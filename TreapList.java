import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Stack;


public class TreapList<E> implements List<E> {
	
	private TreapNode<E> root;
    private E returndata;
	
	// A Treap Node
	private static class TreapNode<E>
	{
	    E data;
	    int priority;
	    int treecount;
	    TreapNode<E> left, right;
	 
	    // constructor
	    public TreapNode(E data)
	    {
	        this.treecount=0;
	        this.data = data;
	        this.priority = new Random().nextInt(100);
	        this.left = this.right = null;
	    }
	}

	   // TreapNode root;
	public TreapList()
	    {
	        this.root=null;
            this.returndata=null;
	    }

	   
	 
	    public boolean add(E e)
	    {
	    	add(size(), e);
			return true;
	    }
	    
	    public void add(int index, E e)
	    {
	        TreapNode<E> currroot=addNode(this.root, index, e);
	        this.root =currroot ;
	    }
	    
	    
	    // Recursive function to search for a key in a given treap
	    public E get(int index){
	        return searchNode(this.root, index );
	    }
	    
	    
	    
	public int size()
	    {
	       if(this.root!=null) 
	       {
	    	 return this.root.treecount;
	       }
		   return 0;
	    }
	        
	 

	public void clear()
	{
	    this.root=null;
	}
	    
	 // Recursive function to delete a key from a given treap
	public E remove(int index)
	{
        this.root=deleteNode(this.root, index);
        E data=this.returndata;
        
        
        this.returndata=null;
        return data;
		//return null;
	}
	
	
	
	public Iterator<E> iterator() 
	{
		return new HelperIterator(this.root);
	}
	
	
	
	@Override
	public String toString()
		{   if (size() == 0) return "[]";
			StringBuilder sb = new StringBuilder();
			sb.append('[');
			sb.append(inorder(this.root));
			sb.append(']');
			
			return sb.toString();
		}
	
	
	
	
	/*
	 * -----------------------------------------------------------------------------------------------------------------------------------
	 * Private methods for helping
	 */
	private class HelperIterator implements Iterator<E> 
	{
		private Stack<TreapNode<E>> current ;
		
		HelperIterator(TreapNode<E> root)
	    {
	        current = new Stack<TreapNode<E>>();
	        CheckLeft(root);
	    }
		
		private void CheckLeft(TreapNode<E> newNode)
	    {
	        while (newNode != null) {
	            current.push(newNode);
	            newNode = newNode.left;
	        }
	    }

		@Override
		public boolean hasNext() {
			
			return !current.isEmpty();
		}


		@Override
		public E next() {
			if (!hasNext())
			{ throw new IndexOutOfBoundsException();}
			
			TreapNode<E> curr = current.pop();
			 
	        if (curr.right != null)
	            CheckLeft(curr.right);
	 
	        return curr.data;
		}
		
	}
	
	
	private TreapNode<E> addNode(TreapNode<E> root, int index, E data)
    {
	 if (index < 0 || index > size()) { throw new IndexOutOfBoundsException();}
        // base case
     if (root == null) 
        {
            root=new TreapNode<E>(data);
			root.treecount=1;
			return root;
        }
 
        // if data is less than the root node, insert in the left subtree;
        // otherwise, insert in the right subtree
    if(root.left!=null)
        {
        	if (index<= root.left.treecount)
        		{
        			root.left = addNode(root.left, index, data);
 
            // rotate right if heap property is violated
        			if (root.left != null && root.left.priority > root.priority) 
        			{
        				root = rotateRight(root);
        			}

        		}
     		
            		index=index-root.left.treecount;
        		
        
    	}
     if(index==0)
	{
        root.left = newNode(root.left, data);
		            // rotate right if heap property is violated
			if (root.left != null && root.left.priority > root.priority) 
				{
					root = rotateRight(root);
				}

    }


    else if(index>0) 
		{
            root.right = addNode(root.right,index-1, data);
		
 
            // rotate left if heap property is violated
            	if (root.right != null && root.right.priority > root.priority) 
					{
               		 	root = rotateLeft(root);
            		}
        }
        root.treecount++;
        return root;
    }
	



	private  TreapNode<E> newNode(TreapNode<E> root, E data){
		if(root==null){
			root=new TreapNode<E>(data);
		}
		else{
			root.right=newNode(root.right,data);
			if (root.right != null && root.right.priority > root.priority) {
                root = rotateLeft(root);
            }
		}
		root.treecount++;
		return root;

	}
	
	private E searchNode(TreapNode<E> root, int key)
    {
		if (key < 0 || key > size()) { throw new IndexOutOfBoundsException();}
        // if the key is not present in the tree
        if (root == null) 
        {
            throw new IndexOutOfBoundsException();
        }
 
        // if the key is found	 
        // if the key is less than the root node, search in the left subtree
        if (root.left!=null){
        if(key < root.left.treecount) {
            return searchNode(root.left, key);
        }
        key-=root.left.treecount;
    }
        if (key==0) {
            return root.data;
        } 
        // otherwise, search in the right subtree
        return searchNode(root.right, key-1);
    }
	  
	
	
	
	
	private  TreapNode<E> deleteNode(TreapNode<E> root, int key)
	    {
		    if (key < 0 || key > size()) { throw new IndexOutOfBoundsException();}
	        if (root == null) {
	            return null;
	        }
	      
	 
	        // if the key is found

	 
	        // if the key is less than the root node, search in the left subtree
	        if (root.left!=null){
	        if(key < root.left.treecount) {
	            TreapNode<E> returns=deleteNode(root.left, key);
	            root.left=returns;
//	            data=returns.getValue();

	        }
	        else{key-=root.left.treecount;}
	        }
	        if(key==0) {
	            this.returndata=root.data;
	            // Case 1: node to be deleted has no children (it is a leaf node)
	            if (root.left == null && root.right == null)
	            {
	                // deallocate the memory and update root to null
	                root = null;
	            }
	 
	            // Case 2: node to be deleted has two children
	            else if (root.left != null && root.right != null)
	            {
	                // if the left child has less priority than the right child
	                if (root.left.priority < root.right.priority)
	                {
	                    // call `rotateLeft()` on the root
	                    root = rotateLeft(root);
	 
	                    // recursively delete the left child
	                    root.left = deleteNode(root.left, 0);
	                }
	                else {
	                    // call `rotateRight()` on the root
	                    root = rotateRight(root);
	 
	                    // recursively delete the right child
	                    root.right = deleteNode(root.right, 0);
	                }
	            }
	 
	            // Case 3: node to be deleted has only one child
	            else {
	                // choose a child node
	                TreapNode<E> child = (root.left != null)? root.left: root.right;
	                root = child;
	            }
	        }
	    

	       else  {
            key--;
	        TreapNode<E> returns=deleteNode(root.right, key);
	        root.right=returns;
	        }
	 
	        // if the key is found
	       
	        root.treecount--;
	        return root;
	    }
	    
	    
	    
	    
	   
	    
	 
	    // Utility function to print two-dimensional view of a treap using
	    // reverse inorder traversal
//	    public String printTreap(TreapNode<E> root, int space)
//	    {
//	        final int height = 10;
//	        String returnvalue=null;
//	 
//	        // Base case
//	        if (root == null) 
//	        {
//	            return returnvalue;
//	        }
//	 
//	        // increase distance between levels
//	        space += height;
//	 
//	        // print the left child first
//	        returnvalue+=printTreap(root.left, space);
//	        System.lineSeparator();
//	 
//	        // print the current node after padding with spaces
//	        for (int i = height; i < space; i++) 
//	        {
//	            returnvalue+=", ";
//	        }
//	 
//	        returnvalue+=(root.data + "[" + root.priority + "]\n");
//	 
//	        // print the left child
//	        System.lineSeparator();
//	        returnvalue+=printTreap(root.right, space);
//	        return returnvalue;
//	    }

	

	    private TreapNode<E> rotateLeft(TreapNode<E> root)
	    {
	        TreapNode<E> R = root.right;
	        TreapNode<E> X = root.right.left;
	 
	        // rotate
	        R.left = root;
	        root.right = X;
			R.treecount=root.treecount;
			root.treecount=1;
			if(root.right!=null){
			  root.treecount+=root.right.treecount;
	  
			}
			if(root.left!=null){
			  root.treecount+=root.left.treecount;
	  
			}
	 
	        // set a new root
	        return R;
	    }
	
	    /* Function to left-rotate a given treap
		 
        r                         R
       / \      Left Rotate      / \
      L   R        ———>         r   Y
         / \                   / \
        X   Y                 L   X
  */
  

  /* Function to right-rotate a given treap

          r                        L
         / \     Right Rotate     / \
        L   R        ———>        X   r
       / \                          / \
      X   Y                        Y   R
  */
  private TreapNode<E> rotateRight(TreapNode<E> root)
  {
      TreapNode<E> L = root.left;
      TreapNode<E> Y = root.left.right;
	  

      // rotate
      L.right = root;
      root.left = Y;
	  L.treecount=root.treecount;
	  root.treecount=1;
	  if(root.right!=null){
		root.treecount+=root.right.treecount;

	  }
	  if(root.left!=null){
		root.treecount+=root.left.treecount;

	  }

      // set a new root
      return L;
  }
  
  
  private String inorder(TreapNode<E> root) 
	{     
	  //Base Case
      if(root == null) 
      {
        return "";
      }
      
      String left = inorder(root.left);
      if(!left.isEmpty()) 
      {
    	  left = left + ", ";
      }
      
      String right = inorder(root.right); 
      if(!right.isEmpty()) 
      {
    	  right = ", "+right ;
      }
      
      
      return left + root.data + right;
	}
	/*
	 * ------------------------------------------------------------------------------------------
	 * End of Private methods
	 */
	



	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
	     return false;
	}
	
	


	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}

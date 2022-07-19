import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
//import javafx.util.Pair;

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

	    /* Function to left-rotate a given treap
	 
	          r                         R
	         / \      Left Rotate      / \
	        L   R        ———>         r   Y
	           / \                   / \
	          X   Y                 L   X
	    */
	    private TreapNode<E> rotateLeft(TreapNode<E> root)
	    {
	        TreapNode<E> R = root.right;
	        TreapNode<E> X = root.right.left;
	 
	        // rotate
	        R.left = root;
	        root.right = X;
	 
	        // set a new root
	        return R;
	    }
	 
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
	 
	        // set a new root
	        return L;
	    }
	 
	    public boolean add(E e)
	    {
	    	add(size(), e);
			return true;
	    }
	    
	    public void add(int index, E e)
	    {
	        TreapNode<E> currroot=add(this.root, index, e);
	        this.root =currroot ;
	    }
	     // Recursive function to insert a given key with a priority into treap
	     //returns the base of the tree after completion
	     private TreapNode<E> add(TreapNode<E> root, int index, E data)
	    {
	        // base case
	        if (root == null) {
	            return new TreapNode<E>(data);
	        }
	 
	        // if data is less than the root node, insert in the left subtree;
	        // otherwise, insert in the right subtree
	        if(root.left!=null){
	        if (index<= root.left.treecount)
	        {
	            root.left = add(root.left, index, data);
	 
	            // rotate right if heap property is violated
	             if (root.left != null && root.left.priority > root.priority) {
	                root = rotateRight(root);
	            }

	        }
	        else{
	            index=index-root.left.treecount;
	        }
	        
	    }
	    else if(index==0){
	        root.left = add(root.left, index, data);

	    }


	        else {
	            root.right = add(root.right, index-1, data);
	 
	            // rotate left if heap property is violated
	            if (root.right != null && root.right.priority > root.priority) {
	                root = rotateLeft(root);
	            }
	        }
	        root.treecount++;
	        return root;
	    }
	     
	     
	     
	    // Recursive function to search for a key in a given treap
	    public E get(int index){
	        return searchNode(this.root, index );
	    }
	    private E searchNode(TreapNode<E> root, int key)
	    {
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
	 

	public void clear(){
	    this.root=null;
	}
	    
	 // Recursive function to delete a key from a given treap
	public E remove(int index)
	{
        E data=this.returndata;
        this.returndata=null;
        return data;
		//return null;
	}
	    
	private  TreapNode<E> deleteNode(TreapNode<E> root, int key)
	    {
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
	                    root.left = deleteNode(root.left, 0).getkey();
	                }
	                else {
	                    // call `rotateRight()` on the root
	                    root = rotateRight(root);
	 
	                    // recursively delete the right child
	                    root.right = deleteNode(root.right, 0).getkey();
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
	        TreapNode<E> returns=deleteNode(root.right, key);
	        root.right=returns;
	        }
	 
	        // if the key is found
	       
	        root.treecount--;
	        return root;
	    }
	    
	    
	    
	    
	    public int size()
	    {
	       if(this.root!=null) return this.root.treecount;
		   return 0;
	    }
	    
	    
	 
	    // Utility function to print two-dimensional view of a treap using
	    // reverse inorder traversal
	    public String printTreap(TreapNode<E> root, int space)
	    {
	        final int height = 10;
	        String returnvalue=null;
	 
	        // Base case
	        if (root == null) 
	        {
	            return returnvalue;
	        }
	 
	        // increase distance between levels
	        space += height;
	 
	        // print the left child first
	        returnvalue+=printTreap(root.left, space);
	        System.lineSeparator();
	 
	        // print the current node after padding with spaces
	        for (int i = height; i < space; i++) 
	        {
	            returnvalue+=", ";
	        }
	 
	        returnvalue+=(root.data + "[" + root.priority + "]\n");
	 
	        // print the left child
	        System.lineSeparator();
	        returnvalue+=printTreap(root.right, space);
	        return returnvalue;
	    }

	 /** 
	    public static void main(String[] args)
	    {
	        // Treap keys
	        int[] keys = { 5, 2, 1, 4, 9, 8, 10 };
	 
	        // construct a treap
	        TreapNode root = null;
	        for (int key: keys) {
	            root = insertNode(root, key);
	        }
	 
	        System.out.println("Constructed treap:\n\n");
	        printTreap(root, 0);
	 
	        System.out.println("\nDeleting node 1:\n\n");
	        root = deleteNode(root, 1);
	        printTreap(root, 0);
	 
	        System.out.println("\nDeleting node 5:\n\n");
	        root = deleteNode(root, 5);
	        printTreap(root, 0);
	 
	        System.out.println("\nDeleting node 9:\n\n");
	        root = deleteNode(root, 9);
	        printTreap(root, 0);
	    }*/
   
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
	     return false;
	}
	
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		
		return null;
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
	public Object[] toArray(Object[] a) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public Object set(int index, Object element) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public void add(int index, Object element) {
//		// TODO Auto-generated method stub
//		
//	}


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
	public ListIterator listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}

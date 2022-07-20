import java.util.ArrayList;
import java.util.Iterator;

public class RunTester {
   public static void main(String[] args) {
        TreapList<String> testlist=new TreapList<String>();
        ArrayList<String> baselist=new ArrayList<String>();
        testlist.add("Hello");
        baselist.add("Hello");
        
        System.out.println(testlist.get(0));

        System.out.println(baselist.get(0));
        String j=testlist.toString();
        System.out.println(j);

        System.out.println(baselist.toString());
        for(int i=1; i<11; i++){
            String istring=""+i;
            testlist.add(i,istring);
            baselist.add(i,istring);
            System.out.println(i);
            j=testlist.toString();
            System.out.println(j);
            //System.out.println(testlist.printTreap(11));
    
            System.out.println(baselist.toString());

        }
        j=testlist.toString();
        System.out.println(j);

        System.out.println(baselist.toString());
        System.out.println(testlist.get(5));
        System.out.println(baselist.get(5));
        System.out.println(testlist.printTreap(0));
        testlist.remove(5);
        baselist.remove(5);

        
        System.out.println(testlist.get(5));
        System.out.println(baselist.get(5));
        testlist.add(5,"5");
        baselist.add(5,"5");
        
        System.out.println(testlist.get(5));
        System.out.println(baselist.get(5));
        String s = " ";
        System.out.println(s);
        Iterator test=testlist.iterator();
        Iterator base=baselist.iterator();
        for(int i=0; i<testlist.size(); i++){
            System.out.println("I is "+i);
            System.out.println(test.next());
            System.out.println(base.next());
            System.out.println(s);
        }
       
       // testlist.remove(0);
       System.out.println(baselist.size());
        System.out.println(testlist.toString());
    }
    
}

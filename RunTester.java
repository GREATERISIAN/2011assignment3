import java.util.ArrayList;

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

    }
    
}

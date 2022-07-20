import java.util.ArrayList;

public class RunTester {
   public static void main(String[] args) {
        TreapList<String> testlist=new TreapList<String>();
        ArrayList<String> baselist=new ArrayList<String>();
        testlist.add("Hello");
        baselist.add("Hello");
        System.out.println(testlist.get(0));

        System.out.println(baselist.get(0));
        System.out.println(testlist.toString());

        System.out.println(baselist.toString());

    }
    
}

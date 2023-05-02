package computerrepairservice;

//import java.util.Properties;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;

//import static computerrepairservice.Component.testNumber;

public class Diagnostic implements NumberComponent{
    public int status; //0 == good; 1 == repair; 2 == replace
    public static int testNumber = 0;
    
    public Diagnostic(int status) {
        this.status = status;
    }
    
    public Diagnostic() {}
    
    public void result(String component, int status) {
        switch(status) {
            case(1):
                System.out.println(component + " needs to be repaired.");
                break;
            case(2):
                System.out.println(component + " needs to be replaced.");
                break;
            case(3):
                System.out.println(component + " is working just fine.");
                break;
            default:
                break;
        }
    }
    
    @Override public String toString() {
        return("Total number of different components that will be analyzed: " + NUM_COMPONENTS);
    }
    
    //print which diagnosis you are performing out of the total.
    //must be declared inside same class to call without object.
    public static void printTestNumber() {
        testNumber++;
        System.out.println("Diagnosis Test #" + testNumber + ":");
    }
    
    //make sure there is a computer present in order to perform any diagnosis
    public void computerExists() {
        try {
            Class.forName("Computer");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void listOfStats(Set<String> s, List<String> l) {
        Map<String, String> mp = new HashMap<>();
        
        //convert both set and list to arrays
        String[] sarray = s.toArray(new String[s.size()]);
        String[] larray = l.toArray(new String[l.size()]);
        
        for(int i = 0; i < NUM_COMPONENTS; i++) {
            switch (larray[i]) {
                case "1":
                    mp.put(sarray[i], "Repair");
                    break;
                case "2":
                    mp.put(sarray[i], "Replace");
                    break;
                case "3":
                    mp.put(sarray[i], "Working");
                    break;
                default:
                    break;
            }
        }
        
        //traverse through Map using for-each loop
        for (Map.Entry<String, String> ma : mp.entrySet()) {

            System.out.print(ma.getKey() + ":");
            System.out.println(ma.getValue());
        }
        System.out.println();
    }
}

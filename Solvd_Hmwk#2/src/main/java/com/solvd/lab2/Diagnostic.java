package computerrepairservice;

//import java.util.Properties;
import computerrepairservice.interfaces.NumberComponent;
import computerrepairservice.linkedlist.LinkedListCustom;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;
//import java.util.function.ObjIntConsumer;
import computerrepairservice.lambdas.ObjIntConsumer;
        
public class Diagnostic implements NumberComponent{
    public int status; //0 == good; 1 == repair; 2 == replace
    public static int testNumber = 0;
    
    //a collection for storing the amount of damage: LinkedList
    LinkedListCustom<Double> lstDamage = new LinkedListCustom<>();
    //a collection for storing each individual price: LinkedList
    LinkedListCustom<Integer> lstPrice = new LinkedListCustom<>();
    //a collection for storing the time required to fix each component: LinkedList
    LinkedListCustom<Double> lstTime = new LinkedListCustom<>();
    
    //partition/group components by repair, replace, and working: Map
    Map<String, String> mp = new HashMap<>();
        
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
    
    public void listOfStats(Set<String> s, List<Integer> l) {
        //convert both set and list to arrays
        String[] sarray = s.toArray(new String[s.size()]);
        Integer[] larray = l.toArray(new Integer[l.size()]);
        
        //Use ObjIntConsumer<T> to check the List for any components that are still working, i.e. reuire no payment
        ObjIntConsumer<List<Integer>> consumerObj = (t, value) -> {
            if(t.contains(value)) {
                System.out.println("Among the diagnosed components, least one requires no fixing.");
            }
        };
                
        consumerObj.accept(l, 3); //use object l from List<Integer> to check for category "Working" (3)
        
        for(int i = 0; i < NUM_COMPONENTS; i++) {
            switch (larray[i]) {
                case 1:
                    mp.put(sarray[i], "Repair");
                    break;
                case 2:
                    mp.put(sarray[i], "Replace");
                    break;
                case 3:
                    mp.put(sarray[i], "Working");
                    break;
                default:
                    break;
            }
        }
        
        //traverse through Map using for-each loop
        for (Map.Entry<String, String> ma : mp.entrySet()) {

            System.out.print(ma.getKey() + ": ");
            System.out.println(ma.getValue());
        }
        System.out.println();
    }
    
    //save all damage amounts
    public void recordDamage(double damage) {
        lstDamage.add(damage);
        System.out.print("Analysis damage(%): ");
        lstDamage.printList();
    }
    
    //save all price estimates for each broken component
    public void recordPrice(int price) {
        lstPrice.add(price);
        System.out.print("Estimated price values: ");
        lstPrice.printList();
    }
    
    //save all time estimates it would take to fix each component
    public void recordTime(double time) {
        lstTime.add(time);
        System.out.print("Estimated number of days: ");
        lstTime.printList();
        System.out.println();
    }
    
}

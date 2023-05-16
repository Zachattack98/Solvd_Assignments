package com.solvd.lab2;

//import java.util.Properties;
import com.solvd.lab2.interfaces.NumberComponent;
import com.solvd.lab2.linkedlist.LinkedListCustom;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;
import com.solvd.lab2.lambdas.ObjIntConsumer;
import java.util.stream.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
                log.info(component + " needs to be repaired.");
                break;
            case(2):
                log.info(component + " needs to be replaced.");
                break;
            case(3):
                log.info(component + " is working just fine.");
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
        log.info("Diagnosis Test #" + testNumber + ":");
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

    public void listOfStats(Set<String> s, List<Integer> l, ObjIntConsumer<List<Integer>> consumerObj) {
        //convert both set and list to arrays
        String[] sarray = s.toArray(new String[s.size()]);
        Integer[] larray = l.toArray(new Integer[l.size()]);
        Stream<String> setStream = s.stream();

        log.info("Sorted set of components:");
        //After diagnosis, use sorted method from stream() to sort all components alphabetically
        Set<String> sortedComponents = setStream.sorted().collect(Collectors.toSet());
        log.info(sortedComponents);

        //Use ObjIntConsumer<T> to check the List for any components that are still working, i.e. require no payment
        consumerObj = (t, value) -> {
            if(t.contains(value)) {
                log.info("Among the diagnosed components, at least one requires no fixing:");
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

        log.info("The remaining components need to be repaired or replaced:");

        //check for all value in hashmap that begin with R (Repair/Replace)
        List<String> result = mp.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
        Stream<String> listStream = result.stream();
        List<String> result2 = listStream.filter(r->r.startsWith("R")).collect(Collectors.toList());

        Name n = null;
        //check the set for names of all components in need of repair/replace
        Set<String> sortedBrokenComponents = n.getName().stream.
                                             filter((Stat st) -> ((st.getStatComponent == Stat.REPAIR) || (st.getStatComponent == Stat.REPLACE))).
                                             collect(Collectors.toSet());

        log.info(sortedBrokenComponents + " : " + result2);
    }

    //save all damage amounts
    public void recordDamage(double damage) {
        lstDamage.add(damage);
        log.info("Analysis damage(%): ");
        lstDamage.printList();
    }

    //save all price estimates for each broken component
    public void recordPrice(int price) {
        lstPrice.add(price);
        log.info("Estimated price values: ");
        lstPrice.printList();
    }

    //save all time estimates it would take to fix each component
    public void recordTime(double time) {
        lstTime.add(time);
        log.info("Estimated number of days: ");
        lstTime.printList();
        System.out.println();
    }

}
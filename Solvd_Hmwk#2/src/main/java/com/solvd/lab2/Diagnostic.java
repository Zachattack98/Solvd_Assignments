package com.solvd.lab2;

//import java.util.Properties;
import com.solvd.lab2.interfaces.NumberComponent;
import com.solvd.lab2.linkedlist.LinkedListCustom;

import java.util.*;

import com.solvd.lab2.lambdas.ObjIntConsumer;
import java.util.stream.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Diagnostic implements NumberComponent{
    public static int testNumber = 0;

    //a collection for storing the amount of damage: LinkedList
    LinkedListCustom<Double> linkedlistDamage = new LinkedListCustom<>();
    //a collection for storing each individual price: LinkedList
    LinkedListCustom<Integer> linkedlistPrice = new LinkedListCustom<>();
    //a collection for storing the time required to fix each component: LinkedList
    LinkedListCustom<Double> linkedlistTime = new LinkedListCustom<>();

    //partition/group components by repair, replace, and working: Map
    Map<String, String> mp = new HashMap<>();

    protected static final Logger DIAGNOSTIC_LOGGER = LogManager.getLogger(Diagnostic.class);

    public Diagnostic() {}

    public void result(String component, int status) {
        switch(status) {
            case(1):
                DIAGNOSTIC_LOGGER.info(component + " needs to be repaired.");
                break;
            case(2):
                DIAGNOSTIC_LOGGER.info(component + " needs to be replaced.");
                break;
            case(3):
                DIAGNOSTIC_LOGGER.info(component + " is working just fine.");
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
        DIAGNOSTIC_LOGGER.info("Diagnosis Test #" + testNumber + ":");
    }

    //make sure there is a computer present in order to perform any diagnosis
    /*public void computerExists() {
        try {
            Class.forName("Computer");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/

    public void listOfStats(Set<String> s, List<Integer> l, ObjIntConsumer<List<Integer>> consumerObj) {
        //convert both set and list to arrays
        String[] sarray = s.toArray(new String[s.size()]);
        Integer[] larray = l.toArray(new Integer[l.size()]);
        ServiceShop ss = null;

        //After diagnosis, use sorted method from stream() to sort all components alphabetically
        Set<String> sortedNames = ss.listOfComponents.stream().sorted().map(Component::getName).collect(Collectors.toCollection(LinkedHashSet::new));
        DIAGNOSTIC_LOGGER.info("Sorted set of components:" + sortedNames);

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

        DIAGNOSTIC_LOGGER.info("These remaining components need to be repaired or replaced:");

        //check for all value in hashmap that begin with R (Repair/Replace)
        List<String> fixResult = mp.entrySet().stream().map(Map.Entry::getValue).
                                filter(r->r.startsWith("R")).collect(Collectors.toList());

        //check the set for names of all components in need of repair/replace
        Set<String> sortedBrokenComponents = mp.entrySet().stream().
                                             filter(p -> p.getValue().startsWith("R")).
                                             map(Map.Entry::getKey).
                                             collect(Collectors.toSet());

        DIAGNOSTIC_LOGGER.info(sortedBrokenComponents + " : " + fixResult);
    }

    //save all damage amounts
    public void recordDamage(double damage) {
        linkedlistDamage.add(damage);
        DIAGNOSTIC_LOGGER.info("Analysis damage(%): ");
        linkedlistDamage.printList();
    }

    //save all price estimates for each broken component
    public void recordPrice(int price) {
        linkedlistPrice.add(price);
        DIAGNOSTIC_LOGGER.info("Estimated price values: ");
        linkedlistPrice.printList();
    }

    //save all time estimates it would take to fix each component
    public void recordTime(double time) {
        linkedlistTime.add(time);
        DIAGNOSTIC_LOGGER.info("Estimated number of days: ");
        linkedlistTime.printList();
    }

}
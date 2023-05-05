package computerrepairservice;

import computerrepairservice.interfaces.ListComponent;
import computerrepairservice.interfaces.NumberComponent;
import computerrepairservice.exception.ComponentNotFoundException;
import computerrepairservice.exception.DamageRangeInvalidException;
import computerrepairservice.exception.PriceInvalidException;
import computerrepairservice.linkedlist.LinkedListCustom;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;

interface Stat {
    //unchangeable static variables for determing repair/replacement status
    public final static int STATUS_REPAIR = 1;
    public final static int STATUS_REPLACE = 2;
    public final static int STATUS_WORKING = 3;
}

public abstract class Component implements Stat, ListComponent, NumberComponent {
    public String nameComponent; //component name
    protected double damage; //damage done to component
    protected int price; //price the component given its specifications
    protected int[] price_array = new int[NUM_COMPONENTS]; //save all prices found in each subclass in this array
    public double time; //number of days the repairs will take
    public double[] time_array = new double[NUM_COMPONENTS]; //save all time intervals found in each subclass in this array
    protected static int priceMultiplier = 2; //for replacement only; multiply the assigned price
    protected static int zeroPrice = 0; //for working only; reassign price as no cost
    
    protected static final Logger COMPONENT_LOGGER = LogManager.getLogger();
    protected Logger logger = COMPONENT_LOGGER;
    
    //a collection for storing the amount of damage: LinkedList
    LinkedListCustom<Double> lst1 = new LinkedListCustom<>();
    //a collection for storing each individual price: LinkedList
    LinkedListCustom<Integer> lst2 = new LinkedListCustom<>();
    //a collection for storing the time required to fix each component: LinkedList
    LinkedListCustom<Double> lst3 = new LinkedListCustom<>();
    
    protected Logger getLogger() {
        return logger;
    }

    protected void setLogger(Logger logger) {
        this.logger = logger;
    }
           
    public Component (String nameComponent, double damage) throws ComponentNotFoundException, DamageRangeInvalidException {
        //if the name of the component being tested is empty, i.e. no component exists
        if(nameComponent.equals("")) {
            throw new ComponentNotFoundException("Component's name not found!");
        } else {
            this.nameComponent = nameComponent;
        }
        //if damage is not between 0 and 100 percent
        if(damage < 0.0 || damage > 100.0) {
            throw new DamageRangeInvalidException("Damage is less than or exceeds the expected range!");
        } else {
            this.damage = damage;
        }
    }
    
    public Component (String nameComponent, double damage, int price) throws ComponentNotFoundException, DamageRangeInvalidException, PriceInvalidException {
        //if the name of the component being tested is empty, i.e. no component exists
        if(nameComponent.equals("")) {
            throw new ComponentNotFoundException("Component's name not found!");
        } else {
            this.nameComponent = nameComponent;
        }
        //if damage is not between 0 and 100 percent
        if(damage < 0.0 || damage > 100.0) {
            throw new DamageRangeInvalidException("Damage is less than or exceeds the expected range!");
        } else {
            this.damage = damage;
        }
        //if price is negative, which is not possible
        if(price < 0) {
            throw new PriceInvalidException("Price for fixing this component does not make sense!");
        } else {
            this.price = price;
        }
    }
    
    @Override 
    public String toString() {
        return ("Fixing the " + nameComponent + " will cost you $" + price + " and will take " + time + " days.");
    }
    
    public void log(Marker marker) {
        if(damage > 0.0) {
            logger.warn(marker, "Damage detected! Analyzing amount.");
        }
    }

    //write to text file
    public static void writeComponentListException() {
        try (
            //Creating an object of FileOutputStream to write stream or raw data
            //Adding resource
            FileOutputStream fos = new FileOutputStream("partsfile.txt")) {
 
            //Custom string input containg the names of all components that will be tested
            String text = "Screen, HardDrive, Adapter, PowerUnit, Fan";
 
            //Converting string to bytes
            byte arr[] = text.getBytes();
 
            //Write the text to partsfile.text
            fos.write(arr);
        }
 
        // Catch block to handle exceptions
        catch (IOException e) {
            // Display message for the occurred exception
            System.out.println(e);
        }
    }
    
    //read from text file
    public static void readComponentListException() {
        //Scanner scanner = null;
        try {
            Scanner scanner = new Scanner(new File("partslist.txt"));
            while (scanner.hasNext()) { //returns true if there's another token/word found in the input
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) { //catch block if the file you're scanning does not exist
            e.printStackTrace(); //pinpoint the exact line in which the method raised the exception
        }
    }
    
    
    //use damage is determine if the component is repairable, replaceable, or still working
    public abstract int statusOfComponent();
    
    //return the price determined in each sub class which will then be added together
    public abstract int determinePrice();
    
    //return the number of days determined in each sub class which will then be added together
    public abstract double determineTime();
    
    public int calculatePrice() {
        int totalPrice = 0;
        for(int i=0; i < NUM_COMPONENTS; i++) {
            totalPrice += price_array[i];
        }
        return totalPrice;
    }

    public double calculateTime() {
        double totalTime = 0;
        for(int i=0; i < NUM_COMPONENTS; i++) {
            totalTime += time_array[i];
        }
        return totalTime;
    }
    
    //save all damage amounts
    public void recordDamage() {
        lst1.add(damage);
        System.out.print("Analysis damage(%): ");
        lst1.printList();
    }
    
    //save all price estimates for each broken component
    public void recordPrice() {
        lst2.add(price);
        System.out.print("Estimated price values: ");
        lst2.printList();
    }
    
    //save all time estimates it would take to fix each component
    public void recordTime() {
        lst3.add(time);
        System.out.print("Estimated number of days: ");
        lst3.printList();
        System.out.println();
    }
}
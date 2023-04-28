package computerrepairservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
 import org.apache.logging.log4j.Marker;
 import org.apache.logging.log4j.MarkerManager;

public class ComputerRepairService {

    public static int testNumber = 0;
    //print which diagnosis you are performing out of the total.
    //must be declared inside same class to call without object.
    public static void printTestNumber() {
        testNumber++;
        System.out.println("Diagnosis Test #" + testNumber + ":");
    }
    //write to text file
    public static void writecomponentlistException() {
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
    public static void readcomponentlistException() {
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
    
    public static void main(String[] args) {
        
        ServiceShop techShop = new ServiceShop("Gyro Tech Computer Repair Service", "Irvine, CA");
        techShop.validShop();
        System.out.println(techShop.toString());
        System.out.println();
        
        writecomponentlistException();
        System.out.println("Here's the list of all components our company typically tests: ");
        readcomponentlistException();
            
        Computer comp = new Computer(4.3, 16, 4);
        comp.validComputer();
        comp.printComputerInfo();
        System.out.println();
        //Error: Yes, No, and everything ele keep turning out invalid
        //comp.powerOnOff();
        
        //if(comp.power.equals("No")) {
            //Laptop lap = new Laptop("Yes");
            //lap.proceed();
            //HomeCom home = new HomeCom("Yes");
            //home.proceed();
            
            Diagnostic diag = new Diagnostic();
            System.out.println(diag.toString());
            System.out.println();
            
            Screen screen = new Screen("LCD Screen", 34.7, 36);
            HardDrive hd = new HardDrive("Hard Drive", 87.4, 20);
            AdapterUSB adapt = new AdapterUSB("USB Adapter(s)", 0.0, 1);
            PowerUnit punit = new PowerUnit("Power Supply Unit", 1.9, 70);
            Fan fan = new Fan("Cooling Fan", 54.0, 25);
            Component[] components = {screen, hd, adapt, punit, fan}; //combine all objects together into a new array
                    
            //create marker object
            Marker marker = MarkerManager.getMarker("CLASS");
             
            int totalCost = 0;
            double totalTime = 0.0;
            //use for-each loop to implement calculatePrice() method in each sub class of Component()
            for(Component component: components) {
                //validate all conditions for components
                component.validComponent();
                component.validDamage();
                component.validPrice();
                component.log(marker);
                //if no errors occurred, begin diagnosis
                printTestNumber();
                totalCost += component.calculatePrice();
                totalTime += component.calculateTime();
                System.out.println(component.toString());
                System.out.println();
            }
        
            //output overall cost and time it will take.
            //Error: time keeps reseting to 0.0, but does assign in the switch case in result()
            System.out.println("Total cost for repairs: $" + totalCost + ", and will take " + totalTime + " days to finish!");
            System.out.println();
        //}
        /*else {
            System.out.println("Alright, thanks for stopping by!");
            System.out.println();
        }*/
    }

}
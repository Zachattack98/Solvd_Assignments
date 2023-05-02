package computerrepairservice;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import java.util.logging.Logger;

public class ComputerRepairService extends Exceptions {
    
    public static void main(String[] args) {
        Logger log = Logger.getLogger("Main");
        
        try {
            ServiceShop techShop = new ServiceShop("Gyro Tech Computer Repair Service", "Irvine, CA");
            //techShop.validShop();
            System.out.println(techShop.toString());
            System.out.println();
        }catch (ShopNotFoundException se) {
            log.info(se.getMessage());
        }
        
        /*writecomponentlistException();
        System.out.println("Here's the list of all components our company typically tests: ");
        readcomponentlistException();*/
         
        try {
            Computer comp = new Computer(4.3, 16, 4);
            //comp.validComputer();
            comp.printComputerInfo();
            System.out.println();
        }catch (ComputerNotFoundException ce) {
            log.info(ce.getMessage());
        }
        
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
                    
            try {
                Set<String> st = new HashSet<>();
                
                Screen screen = new Screen("LCD Screen", 34.7, 36);
                st.add(screen.nameComponent); //adding name component to hash set
                HardDrive hd = new HardDrive("Hard Drive", 87.4, 20);
                st.add(hd.nameComponent);
                AdapterUSB adapt = new AdapterUSB("USB Adapter(s)", 0.0, 1);
                st.add(adapt.nameComponent);
                PowerUnit punit = new PowerUnit("Power Supply Unit", 1.9, 70);
                st.add(punit.nameComponent);
                Fan fan = new Fan("Cooling Fan", 54.0, 25);
                st.add(fan.nameComponent);

                System.out.println("List of components that will be diagnosed: " + st);
                System.out.println();
                
                Component[] components = {screen, hd, adapt, punit, fan}; //combine all objects together into a new array
            
                //create marker object
                Marker marker = MarkerManager.getMarker("CLASS");

                //temporary variables required to output each result outside of for-each loop where object is defined
                int tempcost = 0;
                double temptime = 0;
            
                List<String> lst = new ArrayList<>();
        
                //use for-each loop to implement calculatePrice() method in each sub class of Component()
                for(Component component: components) {
                    //validate all conditions for components
                    component.log(marker);
                    //if no errors occurred, begin diagnosis
                    tempcost = component.calculatePrice();
                    lst.add(Integer.toString(component.statusOfComponent()));
                    temptime = component.calculateTime();
                    
                    diag.printTestNumber();
                    
                    component.recordDamage();
                    component.recordPrice();
                    component.recordTime();
                    
                    System.out.println(component.toString());
                    System.out.println();
                }
                
                diag.listOfStats(st, lst);
                
                //output overall cost and time it will take.
                System.out.println("Total cost for repairs: $" + tempcost + ", and will take " + temptime + " days to finish!");
                System.out.println();
            }catch (ComponentNotFoundException | DamageRangeInvalidException dce) { //multicatch
                log.info(dce.getMessage());
            }
        //}
        /*else {
            System.out.println("Alright, thanks for stopping by!");
            System.out.println();
        }*/
    }

}
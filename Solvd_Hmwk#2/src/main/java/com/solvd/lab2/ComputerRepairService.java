package computerrepairservice;

import computerrepairservice.exception.ShopNotFoundException;
import computerrepairservice.exception.ComputerNotFoundException;
import computerrepairservice.exception.ComponentNotFoundException;
import computerrepairservice.exception.DamageRangeInvalidException;
import computerrepairservice.enums.Name;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ComputerRepairService{
    static Logger log = LogManager.getLogger(ComputerRepairService.class);
    
    public static void main(String[] args) {
        
        try {
            ServiceShop techShop1 = new ServiceShop("Gyro Tech Computer Repair Service", "Irvine, CA");
            //techShop1.validShop();
            log.info(techShop1.toString());
            System.out.println();
        }catch (ShopNotFoundException se) {
            log.info(se.getMessage());
        }
         
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
            
            Name cname = Name.SCREEN; //can be any of the constants, only used to initialize cname
            cname.nameInfo();
            
            UniqueFileWords unique = new UniqueFileWords("*Screen, Motherboard, CPU, *HardDrive, Speakers, *USBAdapter, *PowerUnit, *Fan, Memory, DataStorage");
            unique.uniqueList();
            
            Diagnostic diag = new Diagnostic();
            log.info(diag.toString());
            System.out.println();   
                    
            try {
                ServiceShop techShop2 = new ServiceShop();
                
                //a set collection that contains no duplicates of component names: HashSet
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

                log.info("List of components that will be diagnosed: " + st);
                System.out.println();
                
                Component[] components = {screen, hd, adapt, punit, fan}; //combine all objects together into a new array
            
                //create marker object
                Marker marker = MarkerManager.getMarker("CLASS");

                //List for storing the repair status of each component
                List<Integer> lst = new ArrayList<>();
    
                //use for-each loop to implement calculatePrice() method in each sub class of Component()
                for(Component component: components) {
                    //validate all conditions for components
                    component.log(marker);
                    //if no errors occurred, begin diagnosis
                    component.determinePrice();
                    techShop2.calculatePrice(component.price);
                    //now that we have the status, add it to the list
                    lst.add(component.statusOfComponent());
                    component.determineTime();
                    techShop2.calculateTime(component.time);
                    
                    log.info(techShop2.calculatePrice(component.price));
                    log.info(techShop2.calculateTime(component.time));
                
                    diag.printTestNumber();
                    
                    diag.recordDamage(component.damage);
                    diag.recordPrice(component.price);
                    diag.recordTime(component.time);
                    
                    log.info(component.toString());
                    System.out.println();
                }
                
                diag.listOfStats(st, lst);
                
                techShop2.printPriceAndTime();
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
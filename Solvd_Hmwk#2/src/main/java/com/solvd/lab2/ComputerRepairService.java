package com.solvd.lab2;

import com.solvd.lab2.exception.ShopNotFoundException;
import com.solvd.lab2.exception.ComputerNotFoundException;
import com.solvd.lab2.exception.ComponentNotFoundException;
import com.solvd.lab2.exception.DamageRangeInvalidException;
import com.solvd.lab2.enums.Name;
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

        //initialize objects
        ServiceShop techShop = null;
        Computer comp = null;
        UniqueFileWords unique = null;
        Diagnostic diag = null;

        try {
            ServiceShop techShop = new ServiceShop("Gyro Tech Computer Repair Service", "Irvine, CA");
            //techShop1.validShop();
            log.info(techShop.toString());
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

        UniqueFileWords unique = new UniqueFileWords("*Screen, Motherboard, CPU, *HardDrive, Speakers, *USBAdapter, *PowerUnit, *Fan, Memory, DataStorage");
        unique.uniqueList();

        Diagnostic diag = new Diagnostic();
        log.info(diag.toString());
        System.out.println();

        try {
            techShop.outputToMain(diag, techShop.registerComponent(new Screen("LCD Screen", 34.7, 36),
                                                                  new HardDrive("Hard Drive", 87.4, 20),
                                                                  new AdapterUSB("USB Adapter(s)", 0.0, 1),
                                                                  new PowerUnit("Power Supply Unit", 1.9, 70),
                                                                  new Fan("Cooling Fan", 54.0, 25)););
        }catch (ComponentNotFoundException | DamageRangeInvalidException dce) { //multicatch
            log.info(dce.getMessage());
        }
        //}
        /*else {
            log.info("Alright, thanks for stopping by!");
            System.out.println();
        }*/
    }

}
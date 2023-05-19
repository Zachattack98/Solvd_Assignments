package com.solvd.lab2;

import com.solvd.lab2.exception.ShopNotFoundException;
import com.solvd.lab2.exception.ComputerNotFoundException;
import com.solvd.lab2.exception.ComponentNotFoundException;
import com.solvd.lab2.exception.DamageRangeInvalidException;

import java.io.IOException;

import com.solvd.lab2.uniquewordcounter.UniqueFileWords;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ComputerRepairService{
    private static final Logger mainlog = LogManager.getLogger(ComputerRepairService.class);

    public static void main(String[] args) throws IOException {

        //initialize objects
        ServiceShop techShop = null;
        Computer comp = null;
        Diagnostic diag = null;

        try {
            techShop = new ServiceShop("Gyro Tech Computer Repair Service", "Irvine, CA");
            //techShop1.validShop();
            mainlog.info(techShop.toString());
            System.out.println();
        }catch (ShopNotFoundException se) {
            mainlog.info(se.getMessage());
        }

        try {
            comp = new Computer(4.3, 16, 4);
            //comp.validComputer();
            comp.printComputerInfo();
            System.out.println();
        }catch (ComputerNotFoundException ce) {
            mainlog.info(ce.getMessage());
        }

        //Error: Yes, No, and everything ele keep turning out invalid
        //comp.powerOnOff();

        //if(comp.power.equals("No")) {
        //Laptop lap = new Laptop("Yes");
        //lap.proceed();
        //HomeCom home = new HomeCom("Yes");
        //home.proceed();

        diag = new Diagnostic();
        mainlog.info(diag.toString());
        System.out.println();

        try {

            techShop.registerComponent(new Screen("LCD Screen", 34.7, 36));
            techShop.registerComponent(new HardDrive("Hard Drive", 87.4, 20));
            techShop.registerComponent(new AdapterUSB("USB Adapter(s)", 0.0, 1));
            techShop.registerComponent(new PowerUnit("Power Supply Unit", 1.9, 70));
            techShop.registerComponent(new Fan("Cooling Fan", 54.0, 25));

            techShop.outputToMain(diag);
        }catch (ComponentNotFoundException | DamageRangeInvalidException dce) { //multicatch
            mainlog.info(dce.getMessage());
        }
        //}
        /*else {
            log.info("Alright, thanks for stopping by!");
            System.out.println();
        }*/
    }

}
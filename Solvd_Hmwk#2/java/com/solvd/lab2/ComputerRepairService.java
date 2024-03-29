package com.solvd.lab2;

import com.solvd.lab2.exception.ShopNotFoundException;
import com.solvd.lab2.exception.ComputerNotFoundException;
import com.solvd.lab2.exception.ComponentNotFoundException;
import com.solvd.lab2.exception.DamageRangeInvalidException;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//Error: output does work in NetBeans but cannot build project successfully to test remaining lambdas
public class ComputerRepairService{
    private static final Logger MAIN_LOGGER = LogManager.getLogger(ComputerRepairService.class);

    public static void main(String[] args) {

        //initialize objects
        ServiceShop techShop = null;
        Computer comp = null;
        Diagnostic diag;

        try {
            techShop = new ServiceShop("Gyro Tech Computer Repair Service", "Irvine, CA");
            //techShop1.validShop();
            MAIN_LOGGER.info(techShop.toString());
        }catch (ShopNotFoundException se) {
            MAIN_LOGGER.info(se.getMessage());
        }

        try {
            comp = new Computer(4.3, 16, 4);
            //comp.validComputer();
            comp.printComputerInfo();
        }catch (ComputerNotFoundException ce) {
            MAIN_LOGGER.info(ce.getMessage());
        }

        comp.powerOnOff();

        Laptop lap = new Laptop();
        HomeCom home = new HomeCom();

        if(comp.getPower().equals("No")) {
            //This way we'll only implement proceed() once
            if(lap.weight < 5.0){
                lap.proceed();
            }
            else if(home.weight >= 5.0 && home.weight <= 10.0){
                home.proceed();
            }

            diag = new Diagnostic();
            MAIN_LOGGER.info(diag.toString());

            try {

                assert techShop != null;
                techShop.registerComponent(new Screen("LCD Screen", 34.7, 36));
                techShop.registerComponent(new HardDrive("Hard Drive", 87.4, 20));
                techShop.registerComponent(new AdapterUSB("USB Adapter(s)", 0.0, 1));
                techShop.registerComponent(new PowerUnit("Power Supply Unit", 1.9, 70));
                techShop.registerComponent(new Fan("Cooling Fan", 54.0, 25));

                techShop.outputToMain(diag);
            } catch (ComponentNotFoundException | DamageRangeInvalidException dce) { //multicatch
                MAIN_LOGGER.info(dce.getMessage());
            }
        }
        else {
            MAIN_LOGGER.info("Alright, thanks for stopping by!");
        }
    }

}
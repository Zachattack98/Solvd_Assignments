package com.solvd.lab2;

//import java.util.Properties;
import com.solvd.lab2.exception.ComputerNotFoundException;
import com.solvd.lab2.interfaces.InitVerify;
import com.solvd.lab2.enums.Type;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Computer implements InitVerify {
    protected double weight;
    public int ramSize;
    public int diskSize;
    private String power;

    private static final Logger COMPUTER_LOGGER = LogManager.getLogger();

    public String getPower() {
        return power;
    }

    public void setPower(String power) { this.power = power;}

    public Computer(double weight, int ramSize, int diskSize) throws ComputerNotFoundException {
        //if no viable computer is empty
        if(weight == 0.0 || (diskSize == 0 && ramSize == 0)) {
            throw new ComputerNotFoundException("No computer found to be diagnosed!\n");
        } else {
            this.weight = weight;
            this.ramSize = ramSize;
            this.diskSize = diskSize;
        }
    }

    //need default constructor to create constructors in HomeCom and Laptop
    //classes with the one variable in the parameter
    public Computer() {}

    @Override
    public void printComputerInfo() {
        if(weight < 5.0) {
            Type laptop = Type.LAPTOP;
            COMPUTER_LOGGER.info("Your " + laptop.getType() + " contains " + ramSize + "GB of RAM and " + diskSize + "GB of storage.\n");
        }
        else if(weight >= 5.0 && weight <= 10.0) {
            Type home = Type.HOME;
            COMPUTER_LOGGER.info("Your " + home.getType() + " contains " + ramSize + "GB of RAM and " + diskSize + "GB of storage.\n");
        }
        else {
            COMPUTER_LOGGER.info("Invalid specification of computer model!!\n");
        }
    }

    //ask if the computer initially powers on.
    //should only be initialized in superclass.
    final void powerOnOff() {
        COMPUTER_LOGGER.info("Does your computer turn on?");
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        power = myObj.nextLine();
        while(!power.equals("Yes") && !power.equals("No")) {
            COMPUTER_LOGGER.info("Invalid input! Please type answer again.\n");
            power = myObj.nextLine();
        }
    }
}
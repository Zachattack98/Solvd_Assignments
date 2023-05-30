package com.solvd.lab2;

//import java.util.Properties;
import java.util.Scanner;
import com.solvd.lab2.exception.ComputerNotFoundException;
import com.solvd.lab2.interfaces.InitDiagnosis;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomeCom extends Computer implements InitDiagnosis {
    public String hdecision;

    private static final Logger HOME_LOGGER = LogManager.getLogger(HomeCom.class);

    public HomeCom(double weight, int ramSize, int diskSize, String hdecision) throws ComputerNotFoundException {
        super(weight, ramSize, diskSize); //must be first statement in constructor
        //if no viable computer is empty
        if(weight <= 0.0 || (diskSize <= 0 && ramSize <= 0)) {
            throw new ComputerNotFoundException("No computer found to be diagnosed!\n");
        } else {
            this.weight = weight;
        }
        this.hdecision = hdecision;
    }

    public HomeCom() {}

    @Override
    public void proceed() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        //if it is a home computer and powers on, ask customer if you can proceed with diagnosis
        HOME_LOGGER.info("Do you want me to take a closer look?");
        hdecision = myObj.nextLine();
        if(hdecision.equals("Yes")) {
            HOME_LOGGER.info("Great, I'll proceed with the Diagnosis!\n");
        }
        else if(hdecision.equals("No")) {
            HOME_LOGGER.info("Alright, thanks for stopping by!\n");
            System.exit(0);
        }
    }
}
package com.solvd.lab2;

//import java.util.Properties;
import java.util.Scanner;
import com.solvd.lab2.exception.ComputerNotFoundException;
import com.solvd.lab2.interfaces.InitDiagnosis;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Laptop extends Computer implements InitDiagnosis {
    private String ldecision;

    public String getLdecision() {
        return ldecision;
    }

    public void setLdecision(String ldecision) {
        this.ldecision = ldecision;
    }

    public Laptop(double weight, int ramSize, int diskSize, String ldecision) throws ComputerNotFoundException {
        super(weight, ramSize, diskSize); //must be first statement in constructor

        //if no viable computer is empty
        if(weight == 0.0 || (diskSize == 0 && ramSize == 0)) {
            throw new ComputerNotFoundException("No computer found to be diagnosed!");
        } else {
            this.weight = weight;
        }
        this.ldecision = ldecision;
    }

    public Laptop(String ldecision) {
        this.ldecision = ldecision;
    }

    @Override
    public void proceed() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        //if it is a laptop and powers on, ask customer if you can proceed with diagnosis
        if(weight < 5.0 && power.equals("No")){
            log.info("Do you want me to take a closer look?");
            ldecision = myObj.nextLine();
            if(ldecision.equals("Yes")) {
                log.info("Great, I'll proceed with the Diagnosis!");
                System.out.println();
            }
            else if(ldecision.equals("No")) {
                log.info("Alright, thanks for stopping by!");
            }
        }
    }
}
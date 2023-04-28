package computerrepairservice;

import java.io.*;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;

//cannot declare public because the interface would require its own .java file
interface Stat {
    //unchangeable static variables for determing repair/replacement status
    public final static int STATUS_REPAIR = 1;
    public final static int STATUS_REPLACE = 2;
    public final static int STATUS_WORKING = 3;
}

interface determinePrice {
    public int printPrice();
}

public abstract class Component /*extends IOException*/ implements Stat, determinePrice{
    public String nameComponent; //component name
    protected double damage; //damage done to component
    protected int price; //price the component given its specifications
    protected double time; //number of days the repairs will take
    protected static int priceMultiplier = 2; //for replacement only; multiply the assigned price
    protected static int zeroPrice = 0; //for working only; reassign price as no cost
    
    protected static final Logger COMPONENT_LOGGER = LogManager.getLogger();
    private Logger logger = COMPONENT_LOGGER;

    protected Logger getLogger() {
        return logger;
    }

    protected void setLogger(Logger logger) {
        this.logger = logger;
    }
           
    public Component (String nameComponent, double damage) {
        this.nameComponent = nameComponent;
        this.damage = damage;
    }
    
    //use damage is determine if the component is repairable, replaceable, or still working
    public abstract int statusofComponent();
    
    //return the price determined in each sub class which will then be added together
    public abstract int calculatePrice();
    
    //return the number of days determined in each sub class which will then be added together
    public abstract double calculateTime();
    
    @Override public int printPrice() {
        return price;
    }
    
    @Override public String toString() {
        return ("Fixing the " + nameComponent + " will cost you $" + printPrice());
    }
    
    public void log(Marker marker) {
        if(damage > 0.0) {
            logger.warn(marker, "Damage detected! Analyzing amount.");
        }
    }

    //price must be above $0
    public void validPrice() {
        if(price < 0) {
            try {
                throw new PriceInvalidException("Price for fixing this component does not make sense!");
            }
            catch(PriceInvalidException e) {
                logger.error(e.getMessage());
                System.exit(1);
            }
        }
    }
    
    //damage must be between 0 and 100 percent
    public void validDamage() {
        if(damage < 0.0 || damage > 100.0) {
            try {
                throw new DamageRangeInvalidException("Damage is less than or exceeds the expected range!");
            }
            catch(DamageRangeInvalidException e) {
                logger.error(e.getMessage());
                System.exit(1);
            }
        }
    }
    
    public void validComponent() {
        //if the name of the component being tested is empty, i.e. no component exists
        if(nameComponent.equals("")) {
            try {
                throw new ComponentNotFoundException("Component's name not found!");
            }
            catch(ComponentNotFoundException e) {
                logger.error(e.getMessage());
                System.exit(1);
            }
        }
    }
}
package computerrepairservice;

import computerrepairservice.interfaces.ListComponent;
import computerrepairservice.interfaces.NumberComponent;
import computerrepairservice.exception.ComponentNotFoundException;
import computerrepairservice.exception.DamageRangeInvalidException;
import computerrepairservice.exception.PriceInvalidException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;

public abstract class Component implements ListComponent, NumberComponent {
    public String nameComponent; //component name
    protected double damage; //damage done to component
    protected int price; //price the component given its specifications
    protected double time; //number of days the repairs will take
    protected static int priceMultiplier = 2; //for replacement only; multiply the assigned price
    
    protected Logger componentLogger = LogManager.getLogger();
    
    protected Logger getLogger() {
        return componentLogger;
    }

    protected void setLogger(Logger componentLogger) {
        this.componentLogger = componentLogger;
    }
           
    public Component (String nameComponent, double damage) throws ComponentNotFoundException, DamageRangeInvalidException {
        //if the name of the component being tested is empty, i.e. no component exists
        if(nameComponent.equals("")) {
            throw new ComponentNotFoundException("Component's name not found!");
        } else {
            this.nameComponent = nameComponent;
        }
        //if damage is not between 0 and 100 percent
        if(damage < 0.0 || damage > 100.0) {
            throw new DamageRangeInvalidException("Damage is less than or exceeds the expected range!");
        } else {
            this.damage = damage;
        }
    }
    
    public Component (String nameComponent, double damage, int price) throws ComponentNotFoundException, DamageRangeInvalidException, PriceInvalidException {
        //if the name of the component being tested is empty, i.e. no component exists
        if(nameComponent.equals("")) {
            throw new ComponentNotFoundException("Component's name not found!");
        } else {
            this.nameComponent = nameComponent;
        }
        //if damage is not between 0 and 100 percent
        if(damage < 0.0 || damage > 100.0) {
            throw new DamageRangeInvalidException("Damage is less than or exceeds the expected range!");
        } else {
            this.damage = damage;
        }
        //if price is negative, which is not possible
        if(price < 0) {
            throw new PriceInvalidException("Price for fixing this component does not make sense!");
        } else {
            this.price = price;
        }
    }
    
    @Override 
    public String toString() {
        return ("Fixing the " + nameComponent + " will cost you $" + price + " and will take " + time + " days.");
    }
    
    public void log(Marker marker) {
        if(damage > 0.0) {
            componentLogger.warn(marker, "Damage detected! Analyzing amount.");
        }
    }
    
    //use damage is determine if the component is repairable, replaceable, or still working
    public abstract int statusOfComponent();
    
    //return the price determined in each sub class which will then be added together
    public abstract int determinePrice();
    
    //return the number of days determined in each sub class which will then be added together
    public abstract double determineTime();

}

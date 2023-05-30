package com.solvd.lab2;

import com.solvd.lab2.enums.Time;
import com.solvd.lab2.interfaces.NumberComponent;
import com.solvd.lab2.exception.ComponentNotFoundException;
import com.solvd.lab2.exception.DamageRangeInvalidException;
import com.solvd.lab2.exception.PriceInvalidException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;

import java.util.function.DoublePredicate;
import java.util.function.IntConsumer;

public abstract class Component implements NumberComponent {
    public String nameComponent; //component name
    protected double damage; //damage done to component
    protected int price; //price the component given its specifications
    protected double time; //number of days the repairs will take
    protected static int priceMultiplier = 2; //for replacement only; multiply the assigned price

    DoublePredicate dp = (dmg) -> (dmg >= 0.0 & dmg <= 100.00);

    private static final Logger COMPONENT_LOGGER = LogManager.getLogger();

    public Component (String nameComponent, double damage) throws ComponentNotFoundException, DamageRangeInvalidException {
        //if the name of the component being tested is empty, i.e. no component exists
        if(nameComponent.equals("")) {
            throw new ComponentNotFoundException("Component's name not found!\n");
        } else {
            this.nameComponent = nameComponent;
        }
        //if damage is not between 0 and 100 percent
        if(damage < 0.0 || damage > 100.0) {
            throw new DamageRangeInvalidException("Damage is less than or exceeds the expected range!\n");
        } else {
            this.damage = damage;
        }
    }

    public Component (String nameComponent, double damage, int price) throws ComponentNotFoundException, DamageRangeInvalidException, PriceInvalidException {
        //if the name of the component being tested is empty, i.e. no component exists
        if(nameComponent.equals("")) {
            throw new ComponentNotFoundException("Component's name not found!\n");
        } else {
            this.nameComponent = nameComponent;
        }
        //if damage is not between 0 and 100 percent
        if(damage < 0.0 || damage > 100.0) {
            throw new DamageRangeInvalidException("Damage is less than or exceeds the expected range!\n");
        } else {
            this.damage = damage;
        }
        //if price is negative, which is not possible
        if(price < 0) {
            throw new PriceInvalidException("Price for fixing this component does not make sense!\n");
        } else {
            this.price = price;
        }
    }

    public Component () { }

    public String getName() {
        return nameComponent;
    }
    public double getDamage() { return damage; }
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return ("Fixing the " + nameComponent + " will cost you $" + price + " and will take " + time + " of a day.\n");
    }

    public void log(Marker marker) {
        if(damage > 0.0) {
            COMPONENT_LOGGER.warn(marker, "Damage detected! Analyzing amount.\n");
        }
    }

    //use damage is determine if the component is repairable, replaceable, or still working
    public abstract int statusOfComponent();

    //return the price determined in each sub-class which will then be added together
    public abstract int determinePrice(IntConsumer intC);

    //return the number of days determined in each sub-class which will then be added together
    public double determineTime() {
        Time t;

        switch (statusOfComponent()) {
            case 1:
                t = Time.FULLDAY;
                return t.getTime();
            case 2:
                t = Time.HALFDAY;
                return t.getTime();
            default:
                return 0.0;
        }
    }
}
package com.solvd.lab2;

//import java.util.Properties;
import com.solvd.lab2.exception.ComponentNotFoundException;
import com.solvd.lab2.exception.DamageRangeInvalidException;
import com.solvd.lab2.enums.Stat;
import com.solvd.lab2.enums.Time;
import java.util.function.DoublePredicate;  //lambda expression
import java.util.function.IntConsumer;      //lambda expression
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PowerUnit extends Component {
    private int wattage;

    private static final Logger POWER_LOGGER = LogManager.getLogger(PowerUnit.class);

    public PowerUnit(String nameComponent, double damage, int wattage) throws ComponentNotFoundException, DamageRangeInvalidException {
        super(nameComponent, damage);

        /*try {
            AdapterUSB childPower = new AdapterUSB(nameComponent, damage, wattage);
        }
        catch (ComponentNotFoundException | DamageRangeInvalidException ce) { //multicatch
            logger.error(ce.getMessage());
            System.exit(1);
        }*/

        this.wattage = wattage;
    }

    public int getWattage() {
        return wattage;
    }

    public void setWattage(int wattage) {
        this.wattage = wattage;
    }

    @Override
    public int statusOfComponent() {
        //use DoublePredicate to test for valid damage results

        if(dp.test(damage)) { //if damage is between 0.0 and 100.0 test will return true
            if(damage >= 2.0 && damage <= 37.0) {
                Stat st = Stat.REPAIR;
                return st.getStatComponent();
            }
            else if(damage > 37.0) {
                Stat st = Stat.REPLACE;
                return st.getStatComponent();
            }
            else {
                Stat st = Stat.WORKING;
                return st.getStatComponent();
            }
        }
        else {
            POWER_LOGGER.info("Invalid Damage Calculation!\n");
            return 0;
        }
    }

    @Override
    public int determinePrice(IntConsumer mul) {
        //output the diagnosis results of the power unit
        Diagnostic diag = new Diagnostic();
        diag.result(nameComponent, statusOfComponent());

        if(wattage >= 0 && wattage < 30) {
            price = 25;
        }
        else if(wattage >= 30 && wattage <= 60) {
            price = 40;
        }
        else {
            price = 60;
        }

        if(statusOfComponent() == 2) {
            //create IntConsumer Instance then use accept method to get the price
            mul = p -> price *= priceMultiplier; //double the price if the power unit needs to be replaced
            mul.accept(price);
        }
        else if (statusOfComponent() == 3) {
            price = 0; //no cost for a part that still works
        }

        return price;
    }

    @Override
    public void printTestNumber() {
        POWER_LOGGER.info("Diagnosis Test for Power Unit!");
    }
}
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

public class AdapterUSB extends Component {
    private int numAdapters;

    private static final Logger ADAPTER_LOGGER = LogManager.getLogger(AdapterUSB.class);

    public AdapterUSB(String nameComponent, double damage, int numAdapters) throws ComponentNotFoundException, DamageRangeInvalidException {
        super(nameComponent, damage);

        /*try {
            AdapterUSB childUSB = new AdapterUSB(nameComponent, damage, numAdapters);
        }
        catch (ComponentNotFoundException | DamageRangeInvalidException ce) { //multicatch
            logger.error(ce.getMessage());
            System.exit(1);
        }*/

        this.numAdapters = numAdapters;
    }

    public int getAdapter() {
        return numAdapters;
    }

    public void setAdapter(int numAdapters) {
        this.numAdapters = numAdapters;
    }

    @Override
    public int statusOfComponent(DoublePredicate dp) {
        //use DoublePredicate to test for valid damage results

        if(dp.test(damage)) { //if damage is between 0.0 and 100.0 test will return true
            if(damage >= 9.0 && damage <= 57.0) {
                Stat st = Stat.REPAIR;
                return st.getStatComponent();
            }
            else if(damage > 57.0) {
                Stat st = Stat.REPLACE;
                return st.getStatComponent();
            }
            else {
                Stat st = Stat.WORKING;
                return st.getStatComponent();
            }
        }
        else {
            ADAPTER_LOGGER.info("Invalid Damage Calculation!");
            return 0;
        }
    }


    @Override
    public int determinePrice(IntConsumer mul) {
        //output the diagnosis results of the USB adapter(s)
        Diagnostic diag = new Diagnostic();
        diag.result(nameComponent, statusOfComponent(dmg -> { return (dmg >= 0.0 | dmg <= 100.0); }));

        time = 0.5; //default time for repairing any component; half a day
        switch(numAdapters) {
            case(1):
                price = 15;
                break;
            case(2):
                price = 30;
                break;
            case(3):
                price = 40;
                break;
            default:
                break;
        }

        if(statusOfComponent(dmg -> { return (dmg >= 0.0 | dmg <= 100.0); }) == 2) {
            //create IntConsumer Instance then use accept method to get the price
            mul.accept(price);
        }
        else if (statusOfComponent(dmg -> { return (dmg >= 0.0 | dmg <= 100.0); }) == 3) {
            price = 0; //no cost for a part that still works
        }

        return price;
    }

    @Override
    public double determineTime() {
        Time t;
        switch (statusOfComponent(dmg -> { return (dmg >= 0.0 | dmg <= 100.0); })) {
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
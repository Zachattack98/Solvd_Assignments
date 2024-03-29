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

public class HardDrive extends Component {
    private int gigabytes;

    private static final Logger HD_LOGGER = LogManager.getLogger(HardDrive.class);

    public HardDrive(String nameComponent, double damage, int gigabytes) throws ComponentNotFoundException, DamageRangeInvalidException {
        super(nameComponent, damage);

        /*try {
            AdapterUSB childHardDrive = new AdapterUSB(nameComponent, damage, gigabytes);
        }
        catch (ComponentNotFoundException | DamageRangeInvalidException ce) { //multicatch
            logger.error(ce.getMessage());
            System.exit(1);
        }*/

        this.gigabytes = gigabytes;
    }

    public int getBytes() {
        return gigabytes;
    }

    public void setBytes(int gigabytes) {
        this.gigabytes = gigabytes;
    }

    @Override
    public int statusOfComponent() {
        //use DoublePredicate to test for valid damage results

        if(dp.test(damage)) { //if damage is between 0.0 and 100.0 test will return true
            if(damage >= 4.0 && damage <= 41.0) {
                Stat st = Stat.REPAIR;
                return st.getStatComponent();
            }
            else if(damage > 41.0) {
                Stat st = Stat.REPLACE;
                return st.getStatComponent();
            }
            else {
                Stat st = Stat.WORKING;
                return st.getStatComponent();
            }
        }
        else {
            HD_LOGGER.info("Invalid Damage Calculation!\n");
            return 0;
        }
    }

    @Override
    public int determinePrice(IntConsumer mul) {
        //output the diagnosis results of the hard drive
        Diagnostic diag = new Diagnostic();
        diag.result(nameComponent, statusOfComponent());

        if(gigabytes >= 0 && gigabytes < 32) {
            price = 20;
        }
        else if(gigabytes >= 32 && gigabytes <= 64) {
            price = 30;
        }
        else {
            price = 40;
        }

        if(statusOfComponent() == 2) {
            //create IntConsumer Instance then use accept method to get the price
            mul = p -> price *= priceMultiplier; //double the price if the hard drive needs to be replaced
            mul.accept(price);
        }
        else if (statusOfComponent() == 3) {
            price = 0; //no cost for a part that still works
        }

        return price;
    }

    @Override
    public void printTestNumber() {
        HD_LOGGER.info("Diagnosis Test for Hard Drive!");
    }
}
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

public class Fan extends Component {
    private int speed;

    private static final Logger FAN_LOGGER = LogManager.getLogger(Fan.class);

    public Fan(String nameComponent, double damage, int speed) throws ComponentNotFoundException, DamageRangeInvalidException {
        super(nameComponent, damage);

        /*try {
            AdapterUSB childFan = new AdapterUSB(nameComponent, damage, speed);
        }
        catch (ComponentNotFoundException | DamageRangeInvalidException ce) { //multicatch
            logger.error(ce.getMessage());
            System.exit(1);
        }*/

        this.speed = speed;
    }

    public int getFan() {
        return speed;
    }

    public void setFan(int speed) {
        this.speed = speed;
    }

    @Override
    public int statusOfComponent(DoublePredicate dp) {
        //use DoublePredicate to test for valid damage results

        if(dp.test(damage)) { //if damage is between 0.0 and 100.0 test will return true
            if(damage >= 8.0 && damage <= 69.0) {
                Stat st = Stat.REPAIR;
                return st.getStatComponent();
            }
            else if(damage > 69.0) {
                Stat st = Stat.REPLACE;
                return st.getStatComponent();
            }
            else {
                Stat st = Stat.WORKING;
                return st.getStatComponent();
            }
        }
        else {
            FAN_LOGGER.info("Invalid Damage Calculation!\n");
            return 0;
        }
    }

    @Override
    public int determinePrice(IntConsumer mul) {
        //output the diagnosis results of the cooling fan
        Diagnostic diag = new Diagnostic();
        diag.result(nameComponent, statusOfComponent((dmg) -> (dmg >= 0.0 & dmg <= 100.0)));

        time = 0.5; //default time for repairing any component; half a day
        if(speed >= 0 && speed < 15) {
            price = 10;
        }
        else if(speed >= 15 && speed < 25) {
            price = 20;
        }
        else if(speed >= 25 && speed < 30) {
            price = 30;
        }
        else {
            price = 35;
        }

        if(statusOfComponent((dmg) -> (dmg >= 0.0 & dmg <= 100.0)) == 2) {
            //create IntConsumer Instance then use accept method to get the price
            mul = p -> price *= priceMultiplier; //double the price if the cooling fan needs to be replaced
            mul.accept(price);
        }
        else if (statusOfComponent((dmg) -> (dmg >= 0.0 & dmg <= 100.0)) == 3) {
            price = 0; //no cost for a part that still works
        }

        return price;
    }

    @Override
    public double determineTime() {
        Time t;
        switch (statusOfComponent((dmg) -> (dmg >= 0.0 | dmg <= 100.0))) {
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
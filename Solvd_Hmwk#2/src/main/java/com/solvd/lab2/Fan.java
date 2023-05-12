package computerrepairservice;

//import java.util.Properties;
import computerrepairservice.exception.ComponentNotFoundException;
import computerrepairservice.exception.DamageRangeInvalidException;
import computerrepairservice.enums.Stat;
import computerrepairservice.enums.Time;
import java.util.function.IntConsumer;

public class Fan extends Component {
    private int speed;
    
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
    public int statusOfComponent() {
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

    @Override 
    public int determinePrice() {
        //output the diagnosis results of the cooling fan
        Diagnostic diag = new Diagnostic();
        diag.result(nameComponent, statusOfComponent());
        
        time = 0.5; //default time for repairing any component; half a day
        if(speed <= 15) {
            price = 10;
        }
        else if(speed > 15 && speed <= 25) {
            price = 20;
        }
        else if(speed > 25 && speed <= 30) {
            price = 30;
        }
        else {
            price = 35;
        }
        
        if(statusOfComponent() == 2) {
            //create IntConsumer Instance then use accept method to get the price
            IntConsumer mul = p -> p *= priceMultiplier; //double the price if the cooling fan needs to be replaced
            mul.accept(price);
        }
        else if (statusOfComponent() == 3) {
            price = 0; //no cost for a part that still works
        }
        
        return price;
    }

    @Override
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

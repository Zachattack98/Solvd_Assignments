package computerrepairservice;

//import java.util.Properties;
import computerrepairservice.exception.ComponentNotFoundException;
import computerrepairservice.exception.DamageRangeInvalidException;
import computerrepairservice.enums.Stat;
import computerrepairservice.enums.Time;
import java.util.function.IntConsumer;

public class Screen extends Component {
    private int screensz;
    
    public Screen(String nameComponent, double damage, int screensz) throws ComponentNotFoundException, DamageRangeInvalidException {
        super(nameComponent, damage);
        
        /*try {
            AdapterUSB childHardDrive = new AdapterUSB(nameComponent, damage, screensz);
        }
        catch (ComponentNotFoundException | DamageRangeInvalidException ce) { //multicatch
            logger.error(ce.getMessage());
            System.exit(1);
        }*/
        
        this.screensz = screensz;
    }
    
    public int getSize() {
        return screensz;
    }
    
    public void setSize(int screensz) {
        this.screensz = screensz;
    }
    
    @Override 
    public int statusOfComponent() {
        if(damage >= 11.0 && damage <= 53.0) {
            Stat st = Stat.REPAIR;
            return st.getStatComponent();
        }
        else if(damage > 53.0) {
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
        //output the diagnosis results of the LCD screen
        Diagnostic diag = new Diagnostic();
        diag.result(nameComponent, statusOfComponent());
        
        time = 0.5; //default time for repairing any component; half a day
        if(screensz < 32) {
            price = 30;
        }
        else if(screensz >= 32 && screensz <= 40) {
            price = 40;
        }
        else {
            price = 45;
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
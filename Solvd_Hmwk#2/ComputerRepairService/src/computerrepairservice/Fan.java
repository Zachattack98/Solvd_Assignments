package computerrepairservice;

//import java.util.Properties;

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
            return STATUS_REPAIR;
        }
        else if(damage > 69.0) {
            return STATUS_REPLACE;
        }
        else {
            return STATUS_WORKING;
        }
    }

    @Override 
    public int calculatePrice() {
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
            price *= priceMultiplier; //double the price if the cooling fan needs to be replaced
            time = 0.5; //time for replacing any component; one full day
        }
        else if (statusOfComponent() == 3) {
            price = zeroPrice; //no cost for a part that still works
            time = 0.0; //no time necessary for comonents that still work
        }
        
        //add up all individual prices determined in each subclass
        price += price;
        
        return price;
    }

}

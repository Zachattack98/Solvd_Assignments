package computerrepairservice;

//import java.util.Properties;
import computerrepairservice.exception.ComponentNotFoundException;
import computerrepairservice.exception.DamageRangeInvalidException;

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
            return STATUS_REPAIR;
        }
        else if(damage > 53.0) {
            return STATUS_REPLACE;
        }
        else {
            return STATUS_WORKING;
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
            price *= priceMultiplier; //double the price if the cooling fan needs to be replaced
        }
        else if (statusOfComponent() == 3) {
            price = zeroPrice; //no cost for a part that still works
        }
        
        price_array[0] = price;
        
        return price;
    }
    
    @Override
    public double determineTime() {  
        switch (statusOfComponent()) {
            case 1:
                time = 1.0; //time for replacing any component; one full day
                break;
            case 2:
                time = 0.5; //time for replacing any component; one full day
                break;
            case 3:
                time = 0.0; //no time necessary for comonents that still work
                break;
            default:
                break;
        }
        
        time_array[0] = time;

        return time;
    }
    
}
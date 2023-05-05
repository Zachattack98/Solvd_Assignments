package computerrepairservice;

//import java.util.Properties;
import computerrepairservice.exception.ComponentNotFoundException;
import computerrepairservice.exception.DamageRangeInvalidException;

public class PowerUnit extends Component {
    private int wattage;
    
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
        if(damage >= 2.0 && damage <= 37.0) {
            return STATUS_REPAIR;
        }
        else if(damage > 37.0) {
            return STATUS_REPLACE;
        }
        else {
            return STATUS_WORKING;
        }
    }

    @Override 
    public int determinePrice() {
        //output the diagnosis results of the power unit
        Diagnostic diag = new Diagnostic();
        diag.result(nameComponent, statusOfComponent());
        
        time = 0.5; //default time for repairing any component; half a day
        if(wattage <= 30) {
            price = 25;
        }
        else if(wattage > 30 && wattage <= 60) {
            price = 40;
        }
        else {
            price = 60;
        }
        
        if(statusOfComponent() == 2) {
            price *= priceMultiplier; //double the price if the cooling fan needs to be replaced
        }
        else if (statusOfComponent() == 3) {
            price = zeroPrice; //no cost for a part that still works
        }
        
        price_array[3] = price;
        
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
        
        time_array[3] = time;
        
        return time;
    }
}
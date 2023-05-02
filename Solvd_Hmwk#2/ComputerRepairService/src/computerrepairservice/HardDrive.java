package computerrepairservice;

//import java.util.Properties;

public class HardDrive extends Component {
    private int gigabytes;
    
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
        if(damage >= 4.0 && damage <= 41.0) {
            return STATUS_REPAIR;
        }
        else if(damage > 41.0) {
            return STATUS_REPLACE;
        }
        else {
            return STATUS_WORKING;
        }
    }

    @Override 
    public int calculatePrice() {
        //output the diagnosis results of the hard drive
        Diagnostic diag = new Diagnostic();
        diag.result(nameComponent, statusOfComponent());
        
        time = 0.5; //default time for repairing any component; half a day
        if(gigabytes < 32) {
            price = 20;
        }
        else if(gigabytes >= 32 && gigabytes <= 64) {
            price = 30;
        }
        else {
            price = 40;
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

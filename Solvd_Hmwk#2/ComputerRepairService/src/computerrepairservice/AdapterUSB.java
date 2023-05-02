package computerrepairservice;

//import java.util.Properties;


public class AdapterUSB extends Component {
    private int numAdapters;
    
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
    public int statusOfComponent() {
        
        if(damage >= 9.0 && damage <= 57.0) {
            return STATUS_REPAIR;
        }
        else if(damage > 57.0) {
            return STATUS_REPLACE;
        }
        else {
            return STATUS_WORKING;
        }
    }

    
    @Override 
    public int calculatePrice() {
        //output the diagnosis results of the USB adapter(s)
        Diagnostic diag = new Diagnostic();
        diag.result(nameComponent, statusOfComponent());
        
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

package computerrepairservice;

//import java.util.Properties;

public class PowerUnit extends Component {
    private int wattage;
    
    public PowerUnit(String nameComponent, double damage, int wattage) {
        super(nameComponent, damage);
        this.wattage = wattage;
    }
    
    public int getWattage() {
        return wattage;
    }
    
    public void setWattage(int wattage) {
        this.wattage = wattage;
    }

    @Override public int statusofComponent() {
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

    public void powerunitPrice() {
        //output the diagnosis results of the power unit
        Diagnostic diag = new Diagnostic();
        diag.result(nameComponent, statusofComponent());
        
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
        
        if(statusofComponent() == 2) {
            price *= priceMultiplier; //double the price if the cooling fan needs to be replaced
            time = 0.5; //time for replacing any component; one full day
        }
        else if (statusofComponent() == 3) {
            price = zeroPrice; //no cost for a part that still works
            time = 0.0; //no time necessary for comonents that still work
        }
    }
    
    @Override public int calculatePrice() {
        powerunitPrice();
        return price;
    }

    @Override public double calculateTime() {
        return time;
    }
}
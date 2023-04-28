package computerrepairservice;

//import java.util.Properties;

public class Screen extends Component {
    private int screensz;
    
    public Screen(String nameComponent, double damage, int screensz) {
        super(nameComponent, damage);
        this.screensz = screensz;
    }
     
    public int getSize() {
        return screensz;
    }
    
    public void setSize(int screensz) {
        this.screensz = screensz;
    }
    
    @Override public int statusofComponent() {
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

    public void screenPrice() {
        //output the diagnosis results of the LCD screen
        Diagnostic diag = new Diagnostic();
        diag.result(nameComponent, statusofComponent());
        
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
        screenPrice();
        return price;
    }
    
    @Override public double calculateTime() {
        return time;
    }
}

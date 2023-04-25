package computerrepairservice;

//import java.util.Properties;

public class Fan extends Component {
    private int speed;
    
    public Fan(String nameComponent, double damage, int speed) {
        super(nameComponent, damage);
        this.speed = speed;
    }
    
    public int getFan() {
        return speed;
    }
    
    public void setFan(int speed) {
        this.speed = speed;
    }
    
    @Override public int statusofComponent() {
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

    public void fanPrice() {
        //output the diagnosis results of the cooling fan
        Diagnostic diag = new Diagnostic();
        diag.result(nameComponent, statusofComponent());
        
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
        
        if(statusofComponent() == 2) {
            price *= priceMultiplier; //double the price if the cooling fan needs to be replaced
        }
        else if (statusofComponent() == 3) {
            price = zeroPrice; //no cost for a part that still works
        }
    }
    
    @Override public int calculatePrice() {
        fanPrice();
        return price;
    }
    
}

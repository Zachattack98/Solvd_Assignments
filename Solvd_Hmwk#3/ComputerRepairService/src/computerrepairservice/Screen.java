package computerrepairservice;

//import java.util.Properties;

public class Screen extends Components {
    private int screensz;
    
    public int getSize() {
        return screensz;
    }
    
    public void setSize(int screensz) {
        this.screensz = screensz;
    }
    
    public void Screen(int screensz) {
        this.screensz = screensz;
    }
   
    public int screenPrice() {
        Screen myScreen = new Screen();
        if(screensz < 32) {
            myScreen.price = 30;
        }
        else if(screensz >= 32 && screensz <= 40) {
            myScreen.price = 40;
        }
        else {
            myScreen.price = 45;
        }
        return myScreen.price;
    }
    
    @Override public int isRepairable(double damage, int status) {
        if(damage <= 53.0) {
            status = 1;
        }
        else {
            status = 0;
        }
        return status;
    }
    
    @Override public int isReplaceable(double damage, int status) {
        if(damage > 53.0 && damage <= 95.0) {
            status = 2;
        }
        else {
            status = 0;
        }
        return status;
    }
    
}

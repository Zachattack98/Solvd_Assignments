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
   
    public void screenPrice(int screensz) {
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
    }
}

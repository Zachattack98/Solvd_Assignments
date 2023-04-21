package computerrepairservice;

//import java.util.Properties;

public class Fan extends Components {
    private int speed;
    
    public void Fan(int speed) {
        this.speed = speed;
    }
    
    public int getFan() {
        return speed;
    }
    
    public void setFan(int speed) {
        this.speed = speed;
    }
    
    public int fanPrice() {
        Fan myFan = new Fan();
        if(speed <= 15) {
            myFan.price = 10;
        }
        else if(speed > 15 && speed <= 25) {
            myFan.price = 20;
        }
        else if(speed > 25 && speed <= 30) {
            myFan.price = 30;
        }
        else {
            myFan.price = 35;
        }
        return myFan.price;
    }
    
    @Override public int isRepairable(double damage, int status) {
        if(damage <= 69.0) {
            status = 1;
        }
        else {
            status = 0;
        }
        return status;
    }
    
    @Override public int isReplaceable(double damage, int status) {
        if(damage > 69.0 && damage <= 100.0) {
            status = 2;
        }
        else {
            status = 0;
        }
        return status;
    }

}
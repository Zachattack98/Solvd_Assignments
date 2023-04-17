package computerrepairservice;

//import java.util.Properties;

public class Fan extends Components {
    private int speed;
    
    public int getFan() {
        return speed;
    }
    
    public void setFan(int speed) {
        this.speed = speed;
    }
    
    public void Fan(int speed) {
        this.speed = speed;
    }
    
    public void fanPrice(int speed) {
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
    }
}

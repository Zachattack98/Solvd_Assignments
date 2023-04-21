package computerrepairservice;

//import java.util.Properties;

public class PowerUnit extends Components {
    private int wattage;
    
    public void PowerUnit(int wattage) {
        this.wattage = wattage;
    }
    
    public int getWattage() {
        return wattage;
    }
    
    public void setWattage(int wattage) {
        this.wattage = wattage;
    }

    
    public int powerunitPrice() {
        PowerUnit myPowerUnit = new PowerUnit();
        if(wattage <= 30) {
            myPowerUnit.price = 25;
        }
        else if(wattage > 30 && wattage <= 60) {
            myPowerUnit.price = 40;
        }
        else {
            myPowerUnit.price = 60;
        }
        return myPowerUnit.price;
    }
    
    @Override public int isRepairable(double damage, int status) {
        if(damage <= 37.0) {
            status = 1;
        }
        else {
            status = 0;
        }
        return status;
    }
    
    @Override public int isReplaceable(double damage, int status) {
        if(damage > 37.0 && damage <= 98.0) {
            status = 2;
        }
        else {
            status = 0;
        }
        return status;
    }
    
}

package computerrepairservice;

//import java.util.Properties;

public class HardDrive extends Components {
    private int gigabytes;
    
    public void HardDrive(int gigabytes) {
        this.gigabytes = gigabytes;
    }
    
    public int getBytes() {
        return gigabytes;
    }
    
    public void setBytes(int gigabytes) {
        this.gigabytes = gigabytes;
    }
    
    public int harddrivePrice() {
        HardDrive myHardDrive = new HardDrive();
        if(gigabytes < 32) {
            myHardDrive.price = 20;
        }
        else if(gigabytes >= 32 && gigabytes <= 64) {
            myHardDrive.price = 30;
        }
        else {
            myHardDrive.price = 40;
        }
        return myHardDrive.price;
    }
    
    @Override public int isRepairable(double damage, int status) {
        if(damage <= 41.0) {
            status = 1;
        }
        else {
            status = 0;
        }
        return status;
    }
    
    @Override public int isReplaceable(double damage, int status) {
        if(damage > 41.0 && damage <= 82.0) {
            status = 2;
        }
        else {
            status = 0;
        }
        return status;
    }
    
}

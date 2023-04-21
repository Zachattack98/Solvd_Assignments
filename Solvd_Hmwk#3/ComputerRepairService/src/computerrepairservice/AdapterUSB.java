package computerrepairservice;

//import java.util.Properties;

public class AdapterUSB extends Components {
    int numAdapters;
    
    public void AdapterUSB(int numAdapters) {
        this.numAdapters = numAdapters;
    }
    
    public int getAdapter() {
        return numAdapters;
    }
    
    public void setAdapter(int numAdapters) {
        this.numAdapters = numAdapters;
    }

    public int adapterPrice() {
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
        return price;
    }
    
    @Override public int isRepairable(double damage, int status) {
        if(damage <= 57.0) {
            status = 1;
        }
        else {
            status = 0;
        }
        return status;
    }
    
    @Override public int isReplaceable(double damage, int status) {
        if(damage > 57.0 && damage <= 94.0) {
            status = 2;
        }
        else {
            status = 0;
        }
        return status;
    }

}

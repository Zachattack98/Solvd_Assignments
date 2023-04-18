package computerrepairservice;

//import java.util.Properties;

public class AdapterUSB extends Components {
    private int numAdapters;
    
    public int getAdapter() {
        return numAdapters;
    }
    
    public void setAdapter(int numAdapters) {
        this.numAdapters = numAdapters;
    }
    
    public void AdapterUSB(int numAdapters) {
        this.numAdapters = numAdapters;
    }
    
    public int adapterPrice() {
        AdapterUSB myAdapterUSB = new AdapterUSB();
        switch(numAdapters) {
            case(1):
                myAdapterUSB.price = 15;
                break;
            case(2):
                myAdapterUSB.price = 30;
                break;
            case(3):
                myAdapterUSB.price = 40;
                break;
            default:
                break;
        }
        return myAdapterUSB.price;
    }
}

package computerrepairservice;

//import java.util.Properties;

public class AdapterUSB extends Component {
    private int numAdapters;
    
    public AdapterUSB(String nameComponent, double damage, int numAdapters) {
        super(nameComponent, damage);
        this.numAdapters = numAdapters;
    }
    
    public int getAdapter() {
        return numAdapters;
    }
    
    public void setAdapter(int numAdapters) {
        this.numAdapters = numAdapters;
    }
    
    @Override public int statusofComponent() {
        if(damage <= 9.0 && damage <= 57.0) {
            return STATUS_REPAIR;
        }
        else if(damage > 57.0) {
            return STATUS_REPLACE;
        }
        else {
            return STATUS_WORKING;
        }
    }

    public void adapterPrice() {
        //output the diagnosis results of the USB adapter(s)
        Diagnostic diag = new Diagnostic();
        diag.result(nameComponent, statusofComponent());
        
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
        
        if(statusofComponent() == 2) {
            price *= 2; //double the price if the cooling fan needs to be replaced
        }
    }
    
    @Override public int calculatePrice() {
        adapterPrice();
        return price;
    }
}

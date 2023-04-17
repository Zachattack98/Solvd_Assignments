package computerrepairservice;

//import java.util.Properties;

public class Components {
    private int numComponents;
    private boolean status;
    protected double damage;
    protected double price;
    
    public int getNumComponents() {
        return numComponents;
    }
    
    public void setNumComponents(int numComponents) {
        this.numComponents = numComponents;
    }
    
    public void Components(int numComponents, boolean status) {
        this.numComponents = numComponents;
        this.status = status;
    }
    
    public boolean isRepairable() {
        if(damage <= 60.0) {
            status = true;
        }
        else {
            status = false;
        }
        return status;
    }
    
    public boolean isReplaceable() {
        if(damage > 60.0 && damage <= 90.0) {
            status = true;
        }
        else {
            status = false;
        }
        return status;
    }
}

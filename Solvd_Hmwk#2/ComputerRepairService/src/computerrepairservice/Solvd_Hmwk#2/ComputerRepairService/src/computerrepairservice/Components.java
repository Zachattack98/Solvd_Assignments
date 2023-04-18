package computerrepairservice;

//import java.util.Properties;

public class Components {
    private int numComponents;
    //public int status;
    protected double damage;
    protected int price;
    
    public int getNumComponents() {
        return numComponents;
    }
    
    public void setNumComponents(int numComponents) {
        this.numComponents = numComponents;
    }
    
    public void Components(int numComponents) {
        this.numComponents = numComponents;
    }
    
    public void printComponents(int numComponents) {
        System.out.println("Our company can repair/replace a total of " + numComponents + " different components.");
        System.out.println();
    }
    
    /*public int isRepairable(double damage) {
        status = 0;
        if(damage <= 60.0) {
            status = 1;
        }
        return status;
    }
    
    public int isReplaceable(double damage) {
        status = 0;
        if(damage > 60.0 && damage <= 90.0) {
            status = 2;
        }
        return status;
    }*/
}

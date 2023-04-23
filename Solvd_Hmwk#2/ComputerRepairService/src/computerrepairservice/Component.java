package computerrepairservice;

//import java.util.Properties;

public abstract class Component {
    public String nameComponent;
    protected double damage;
    protected int price;
    
    //unchangeable static variables for determing repair/replacement status
    public final static int STATUS_REPAIR = 1;
    public final static int STATUS_REPLACE = 2;
    public final static int STATUS_WORKING = 3;
    
    //total number of components
    public final static int NUM_COMPONENTS = 5;
    
    public Component (String nameComponent, double damage) {
        this.nameComponent = nameComponent;
        this.damage = damage;
    }

    //use damage is determine if the component is repairable, replaceable, or still working
    public abstract int statusofComponent();
    
    //return the price determined in each sub class which will then be added together
    public abstract int calculatePrice();
}
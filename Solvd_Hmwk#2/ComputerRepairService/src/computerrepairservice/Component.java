package computerrepairservice;

//import java.util.Properties;

//cannot declare public because the interface would require its own .java file
interface Stat {
    //unchangeable static variables for determing repair/replacement status
    public final static int STATUS_REPAIR = 1;
    public final static int STATUS_REPLACE = 2;
    public final static int STATUS_WORKING = 3;
}

interface determinePrice {
    public int printPrice();
}

public abstract class Component implements Stat, determinePrice{
    public String nameComponent; //component name
    protected double damage; //damage done to component
    protected int price; //price the component given its specifications
    protected static int priceMultiplier = 2; //for replacement only; multiply the assigned price
    protected static int zeroPrice = 0; //for working only; reassign price as no cost
    
    public Component (String nameComponent, double damage) {
        this.nameComponent = nameComponent;
        this.damage = damage;
    }
    
    //use damage is determine if the component is repairable, replaceable, or still working
    public abstract int statusofComponent();
    
    //return the price determined in each sub class which will then be added together
    public abstract int calculatePrice();
    
    @Override public int printPrice() {
        return price;
    }
    
    @Override public String toString() {
        return ("Fixing the " + nameComponent + " will cost you $" + printPrice());
    }
}
package computerrepairservice;

//import java.util.Properties;

public abstract class Components {
    protected double damage;
    protected int price;
    protected int status;
    
    public void Components(double damage, int price, int status) {
        this.damage = damage;
        this.price = price;
        this.status = status;
    }

    public abstract int isRepairable(double damage, int status);
    public abstract int isReplaceable(double damage, int status);
    
}

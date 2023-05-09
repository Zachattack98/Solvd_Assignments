package computerrepairservice;

import computerrepairservice.exception.ShopNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//cannot be overriden/modified by any subclasses
final class ServiceShop {
    private String nameShop;
    private String location;
    //ArrayList<Integer> arrPrice = new ArrayList<>(NUM_COMPONENTS); //save all prices found in each subclass in this array
    //ArrayList<Double> arrTime = new ArrayList<>(NUM_COMPONENTS); //save all time intervals found in each subclass in this array
    int totalPrice;
    int totalTime;
    
    protected Logger shopLogger = LogManager.getLogger();

    protected Logger getLogger() {
        return shopLogger;
    }

    protected void setLogger(Logger shopLogger) {
        this.shopLogger = shopLogger;
    }
    
    public String getNameShop() {
        return nameShop;
    }
    
    public void setNameShop(String nameShop) {
        this.nameShop = nameShop;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public ServiceShop(String nameShop, String location) throws ShopNotFoundException {
        //if the shop name is empty
        if(nameShop.equals("")) {
            throw new ShopNotFoundException("Shop's name not found!");
        } else {
            this.nameShop = nameShop;
        }
        this.location = location;
    }
    
    public ServiceShop() {}
    
    //static block, called before program even runs
    static {
        System.out.println("Welcome to our Computer Repair shop fine customer!!");
    }
    
    @Override public String toString() {
        return("Here at " + nameShop + ", we offer the best computer repair service in " + location + "!");
    }
    
    public int calculatePrice(int price) {
        totalPrice += price;
        return totalPrice;
    }

    public double calculateTime(double time) {
        totalTime += time;
        return totalTime;
    }
   
}
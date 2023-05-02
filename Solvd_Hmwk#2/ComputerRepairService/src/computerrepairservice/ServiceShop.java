package computerrepairservice;

//import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

 
//cannot be overriden/modified by any subclasses
final class ServiceShop extends Exceptions {
    private String nameShop;
    private String location;
    
    protected static final Logger SHOP_LOGGER = LogManager.getLogger();
    private Logger logger = SHOP_LOGGER;

    protected Logger getLogger() {
        return logger;
    }

    protected void setLogger(Logger logger) {
        this.logger = logger;
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
    
    //static block, called before program even runs
    static {
        System.out.println("Welcome to our Computer Repair shop fine customer!!");
    }
    
    @Override public String toString() {
        return("Here at " + nameShop + ", we offer the best computer repair service in " + location + "!");
    }
    
    /*public void validShop(){
        logger.error(e.getMessage());
        System.exit(1);
    }*/
}
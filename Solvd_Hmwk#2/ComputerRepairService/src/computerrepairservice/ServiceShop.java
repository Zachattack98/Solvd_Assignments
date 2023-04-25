package computerrepairservice;

//import java.util.Properties;
 
//cannot be overriden/modified by any subclasses
final class ServiceShop {
    private String name;
    private String location;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public ServiceShop(String name, String location) {
        this.name = name;
        this.location = location;
    }
    
    //static block, called before program even runs
    static {
        System.out.println("Welcome to our Computer Repair shop fine customer!!");
    }
    
    @Override public String toString() {
        return("Here at " + name + ", we offer the best computer repair service in " + location + "!");
    }
}
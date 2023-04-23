package computerrepairservice;

//import java.util.Properties;
        
public class ServiceShop {
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
    
    @Override public String toString() {
        return("Hello, welcome to " + name + ", the best repair services in " + location + "!");
    }
}
package computerrepairservice;

//import java.util.Properties;
        
public class Shop {
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
    
    public void Shop(String name, String location) {
        this.name = name;
        this.location = location;
    }
    
    public void printShop() {
        System.out.println("Hello, welcome to " + name + ", the best repair services in " + location + "! How may we assist you?");
        System.out.println();
        System.out.println();
    }
}
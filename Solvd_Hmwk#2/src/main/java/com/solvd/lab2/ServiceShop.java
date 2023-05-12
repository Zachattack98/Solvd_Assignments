package computerrepairservice;

import computerrepairservice.enums.Day;
import computerrepairservice.exception.ShopNotFoundException;
import computerrepairservice.lambdas.ToIntFunction;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//cannot be overriden/modified by any subclasses
final class ServiceShop {
    private String nameShop;
    private String location;
    //ArrayList<Integer> arrPrice = new ArrayList<>(NUM_COMPONENTS); //save all prices found in each subclass in this array
    //ArrayList<Double> arrTime = new ArrayList<>(NUM_COMPONENTS); //save all time intervals found in each subclass in this array
    int totalPrice;
    double totalTime;
    
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
   
    public void printPriceAndTime() {
        Math.round(totalTime); //first round the total time up (0.5-0.9) or down (0.0-0.4)
        ToIntFunction<Double> obj = t -> (int)(t*1); //convert double to integer.
                                                     //must multiply by some value to allow conversion.
        System.out.println("What day is it today? ");
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String dayInput = myObj.nextLine();
        
        Day day = null;
        switch(dayInput) {
            case "Monday":
                day.halfOffDay(Day.MONDAY, totalPrice);
                break;
            case "Tuesday":
                day.halfOffDay(Day.TUESDAY, totalPrice);
                break;
            case "Wednesday":
                day.halfOffDay(Day.WEDNESDAY, totalPrice);
                break;
            case "Thursday":
                day.halfOffDay(Day.THURSDAY, totalPrice);
                break;
            case "Friday":
                day.halfOffDay(Day.FRIDAY, totalPrice);
                break;
            case "Saturday":
                day.halfOffDay(Day.SATURDAY, totalPrice);
                break;
            case "Sunday":
                day.halfOffDay(Day.SUNDAY, totalPrice);
                break;
            default:
                System.out.println("Invalid day! We'll just assume is a full price day");
                break;
        }
        
        //output overall cost and time it will take.
        System.out.println("Total cost for repairs: $" + totalPrice + ", and will take " + obj.applyAsInt(totalTime) + " days to finish!");
        System.out.println();
    }
}
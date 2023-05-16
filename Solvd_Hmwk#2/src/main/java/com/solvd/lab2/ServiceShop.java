package com.solvd.lab2;

import com.solvd.lab2.enums.Day;
import com.solvd.lab2.exception.ShopNotFoundException;
import com.solvd.lab2.lambdas.ToIntFunction;

import java.util.*;
import java.util.function.DoublePredicate;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//cannot be overridden/modified by any subclasses
public class ServiceShop extends Component, Diagnostic{
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
        shopLogger.info("Welcome to our Computer Repair shop fine customer!!");
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

    public void printPriceAndTime(ToIntFunction<Double> obj, Function<Integer, Float> factor) {
        Math.round(totalTime); //first round the total time up (0.5-0.9) or down (0.0-0.4)
        obj = t -> (int)(t*1); //convert double to integer.
        //must multiply by some value to allow conversion.
        shopLogger.info("What day is it today? ");
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String dayInput = myObj.nextLine();

        Day day = null;
        switch(dayInput) {
            case "Monday":
                day.halfOffDay(factor, Day.MONDAY, totalPrice);
                break;
            case "Tuesday":
                day.halfOffDay(factor, Day.TUESDAY, totalPrice);
                break;
            case "Wednesday":
                day.halfOffDay(factor, Day.WEDNESDAY, totalPrice);
                break;
            case "Thursday":
                day.halfOffDay(factor, Day.THURSDAY, totalPrice);
                break;
            case "Friday":
                day.halfOffDay(factor, Day.FRIDAY, totalPrice);
                break;
            case "Saturday":
                day.halfOffDay(factor, Day.SATURDAY, totalPrice);
                break;
            case "Sunday":
                day.halfOffDay(factor, Day.SUNDAY, totalPrice);
                break;
            default:
                shopLogger.info("Invalid day! We'll just assume is a full price day");
                break;
        }

        //output overall cost and time it will take.
        shopLogger.info("Total cost for repairs: $" + totalPrice + ", and will take " + obj.applyAsInt(totalTime) + " days to finish!");
        System.out.println();
    }

    public void outputToMain(Diagnostic diag, Component[] components) {
        //a set collection that contains no duplicates of component names: HashSet
        Set<String> st = new HashSet<>();

        Stream<String> setStream = Stream.of(components[0].nameComponent,   //Screen
                                             components[1].nameComponent,   //Hard Drive
                                             components[2].nameComponent,   //USB Adapter
                                             components[3].nameComponent,   //Power Unit
                                             components[4].nameComponent);  //Cooling Fan
        //adding name component to hash set
        setStream.forEach(st::add);

        //Before diagnosis, list names of all components
        shopLogger.info("List of components that will be diagnosed: ");
        setStream.forEach(compnt->log.info("{" + compnt + "} "));

        //create marker object
        //Marker marker = MarkerManager.getMarker("CLASS");

        //List for storing the status of each component
        List<Integer> lstStat = new ArrayList<>();

        //use for-each loop to implement calculatePrice() method in each sub class of Component()
        for (Component component : components) {
            //validate all conditions for components
            component.log(marker);

            //if no errors occurred, begin diagnosis
            //First, price
            component.determinePrice(IntConsumer mul = new IntConsumer);
            calculatePrice(component.price);

            //now that we have the status, add it to the list
            lstStat.add(component.statusOfComponent(DoublePredicate dp = new DoublePredicate));

            //Second, time
            component.determineTime();
            calculateTime(component.time);

            diag.printTestNumber();

            diag.recordDamage(component.damage);
            diag.recordPrice(component.price);
            diag.recordTime(component.time);

            shopLogger.info(component.toString());
            System.out.println();
        }
        diag.listOfStats(st, lstStat, ObjIntConsumer<List<Integer>> consumerObj = new ObjIntConsumer<List<Integer>>);

        //print lists of each individual price and time (minus 0.0 time)
        components.lstPrice.stream().sorted().collect(Collectors.toSet());
        shopLogger.info(components.lstPrice);
        components.lstTime.stream().sorted().filter((Time t) -> ((t.getTime > 0.0)).collect(Collectors.toSet());
        shopLogger.info(components.lstTime);

        //print total price and time
        printPriceAndTime(ToIntFunction<Double> obj = new ToIntFunction<Double>, Function<Integer, Float> factor = new Function<Integer, Float>);
    }

    static Component[] registerComponent(Screen screen, HardDrive hd, AdapterUSB adapt, PowerUnit punit, Fan fan) {
        Component[] components = new Component[5];
        components[0] = screen;
        components[1] = hd;
        components[2] = adapt;
        components[3] = punit;
        components[4] = fan;
        return components;
    }


}
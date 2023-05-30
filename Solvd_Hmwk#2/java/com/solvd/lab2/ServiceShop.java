package com.solvd.lab2;

import com.solvd.lab2.enums.Day;
import com.solvd.lab2.enums.Time;
import com.solvd.lab2.exception.ShopNotFoundException;
import com.solvd.lab2.lambdas.ToIntFunction;

import java.util.*;
import java.util.stream.Collectors;

import com.solvd.lab2.linkedlist.LinkedListCustom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import static com.solvd.lab2.Component.priceMultiplier;
import static com.solvd.lab2.Diagnostic.printTestNumber;

//cannot be overridden/modified by any subclasses
public class ServiceShop {
    private String nameShop;
    private String location;
    int totalPrice;
    double totalTime;
    List<Component> listOfComponents = new ArrayList<>();
    private static final Logger SHOP_LOGGER = LogManager.getLogger(ServiceShop.class);

    public String getNameShop() { return nameShop; }

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
        SHOP_LOGGER.info("Welcome to our Computer Repair shop fine customer!!\n");
    }

    @Override public String toString() {
        return("Here at " + nameShop + ", we offer the best computer repair service in " + location + "!\n");
    }

    public void printPriceAndTime(ToIntFunction<Double> intObj) {
        Math.round(totalTime); //first round the total time up (0.5-0.9) or down (0.0-0.4)

        //Create Set of all constants found in enum Day
        Set<Day> dayNameSet = new HashSet<>();
        dayNameSet.add(Day.MONDAY);
        dayNameSet.add(Day.TUESDAY);
        dayNameSet.add(Day.WEDNESDAY);
        dayNameSet.add(Day.THURSDAY);
        dayNameSet.add(Day.FRIDAY);
        dayNameSet.add(Day.SATURDAY);
        dayNameSet.add(Day.SUNDAY);

        SHOP_LOGGER.info("What day is it today? ");
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String dayInput = myObj.nextLine();

        while(!dayInput.equals("Monday") && !dayInput.equals("Tuesday") && !dayInput.equals("Wednesday")
                && !dayInput.equals("Thursday") && !dayInput.equals("Friday") && !dayInput.equals("Saturday")
                && !dayInput.equals("Sunday")) {
            SHOP_LOGGER.info("Not a valid day, please try again! ");
            myObj = new Scanner(System.in);  // Create a Scanner object
            dayInput = myObj.nextLine();

        }
        //temporary variable required for stream as it only takes those that are final
        String tempInput = dayInput;

        //Only Tuesday and Thursday qre considered half price days
        //anything else, including non-day inputs, give full price
        dayNameSet.stream().filter((day) -> (day.getDayName().equals(tempInput))).
                            filter((half) -> ((half == Day.TUESDAY) || (half == Day.THURSDAY))).map((halfResult) -> {
                            SHOP_LOGGER.info("Half Off Day!!"); return halfResult;}).
                            forEachOrdered((halfCalc) -> { totalPrice = (int)(halfCalc.halfOffDay(p -> p/2.0f, totalPrice));});

        //output overall cost and time it will take.
        SHOP_LOGGER.info("Total cost for repairs: $" + String.format("%.2f", (double)totalPrice) + ", and will take " + intObj.applyAsInt(totalTime) + " days to finish!\n");
    }

    public void outputToMain(Diagnostic diag) {
        Set<String> unsortedNames = listOfComponents.stream().map(Component::getName).collect(Collectors.toSet());
        SHOP_LOGGER.info("List of components that will be diagnosed: "); //Before diagnosis, list names of all components
        unsortedNames.forEach(compnt -> SHOP_LOGGER.info("{" + compnt + "} "));

        //create marker object
        Marker marker = MarkerManager.getMarker("CLASS");

        //List for storing the status of each component
        List<Integer> lstStat = new ArrayList<>();

        //use for-each loop to implement calculatePrice() method in each sub-class of Component()
        for (Component component : listOfComponents) {
            //validate all conditions for components
            component.log(marker);

            //if no errors occurred, begin diagnosis
            //First, price
            component.determinePrice(p -> component.price *= priceMultiplier); //double the price if the cooling fan needs to be replaced
            totalPrice += component.price;

            //now that we have the status, add it to the list
            lstStat.add(component.statusOfComponent((dmg) -> (dmg >= 0.0 & dmg <= 100.0)));

            //Second, time
            component.determineTime();
            totalTime += component.time;

            diag.printTestNumber();

            diag.recordDamage(component.damage);
            diag.recordPrice(component.price);
            diag.recordTime(component.determineTime()); //using component.time would just receive the same value after each iteration

            SHOP_LOGGER.info(component.toString());
            System.out.println();
        }
        //3rd parameter variable: Use ObjIntConsumer<T> to check the List for any components
        //that are still working, i.e. require no payment
        diag.listOfStats(unsortedNames, lstStat, (t, value) -> {
            if(t.contains(value)) {
                SHOP_LOGGER.info("Among the diagnosed components, at least one requires no fixing!");
            }
        });

        //print list of each individual price, damage, and time (minus 0.0 time)
        List<Integer> collectPrice = listOfComponents.stream().filter(p -> p.getPrice() > 0).map(Component::getPrice).collect(Collectors.toList());
        SHOP_LOGGER.info("List of Cost Values:" + collectPrice);
        List<Double> collectDamage = listOfComponents.stream().filter(d -> d.getDamage() > 0.0).map(Component::getDamage).collect(Collectors.toList());
        SHOP_LOGGER.info("List of Damage Dealt:" + collectDamage);

        //print total price and time
        printPriceAndTime(t -> (int)(t*1));
    }

    public void registerComponent(Component components) {
        listOfComponents.add(components);
    }

}
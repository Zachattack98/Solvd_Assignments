package com.solvd.lab2;

import com.solvd.lab2.enums.Day;
import com.solvd.lab2.enums.Time;
import com.solvd.lab2.exception.ShopNotFoundException;
import com.solvd.lab2.lambdas.ToIntFunction;
import com.solvd.lab2.lambdas.Function;

import java.util.*;
import java.util.function.DoublePredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.solvd.lab2.linkedlist.LinkedListCustom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import static com.solvd.lab2.Component.priceMultiplier;

//cannot be overridden/modified by any subclasses
public class ServiceShop {
    private String nameShop;
    private String location;
    int totalPrice;
    double totalTime;
    List<Component> listOfComponents = new ArrayList<>();
    private static final Logger SHOP_LOGGER = LogManager.getLogger(ServiceShop.class);

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
        SHOP_LOGGER.info("Welcome to our Computer Repair shop fine customer!!\n");
    }

    @Override public String toString() {
        return("Here at " + nameShop + ", we offer the best computer repair service in " + location + "!\n");
    }

    public int calculatePrice(int price) {
        totalPrice += price;
        return totalPrice;
    }

    public double calculateTime(double time) {
        totalTime += time;
        return totalTime;
    }

    public void printPriceAndTime(ToIntFunction<Double> intObj, Function<Integer, Float> factor) {
        Math.round(totalTime); //first round the total time up (0.5-0.9) or down (0.0-0.4)
        //must multiply by some value to allow conversion.
        SHOP_LOGGER.info("What day is it today? ");
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String dayInput = myObj.nextLine();

        Day day = Day.valueOf(dayInput);
        //Tuesday and Thursday is half off day; any other day is full price
        switch(day) {
            case MONDAY:
            case WEDNESDAY:
            case FRIDAY:
            case SATURDAY:
            case SUNDAY:
                day.halfOffDay(factor = p -> p, totalPrice);
                break;
            case TUESDAY:
            case THURSDAY:
                day.halfOffDay(factor = p -> p/2.0f, totalPrice);
                break;
            default:
                SHOP_LOGGER.info("Invalid day! We'll just assume is a full price day\n");
                break;
        }

        //output overall cost and time it will take.
        SHOP_LOGGER.info("Total cost for repairs: $" + totalPrice + ", and will take " + intObj.applyAsInt(totalTime) + " days to finish!\n");
    }

    public void outputToMain(Diagnostic diag) {
        /*
        //a set collection that contains no duplicates of component names: HashSet
        Set<String> st = new HashSet<>();

        //Stream<String> setStream = null;
        for (int i = 0; i < NUM_COMPONENTS; i++) {
            setStream = Stream.of(listOfComponents.get(i).nameComponent);
        }
        setStream.forEach(st::add); //adding name component to hash set
         */

        Set<String> unsortedNames = listOfComponents.stream().map(Component::getName).collect(Collectors.toSet());;
        SHOP_LOGGER.info("List of components that will be diagnosed: "); //Before diagnosis, list names of all components
        unsortedNames.forEach(compnt -> SHOP_LOGGER.info("{" + compnt + "} "));

        //create marker object
        Marker marker = MarkerManager.getMarker("CLASS");

        //List for storing the status of each component
        List<Integer> lstStat = new ArrayList<>();

        //List for storing the fixing time for each component
        //needs to be List<Time> to access getTime in stream
        List<Time> listOfComponentTime = new ArrayList<>();

        //use for-each loop to implement calculatePrice() method in each sub-class of Component()
        for (Component component : listOfComponents) {
            //validate all conditions for components
            component.log(marker);

            //if no errors occurred, begin diagnosis
            //First, price
            component.determinePrice(p -> p *= priceMultiplier); //double the price if the cooling fan needs to be replaced
            calculatePrice(component.price);

            //now that we have the status, add it to the list
            lstStat.add(component.statusOfComponent((dmg) -> { return (dmg >= 0.0 | dmg <= 100.0); }));

            //Second, time
            component.determineTime();
            calculateTime(component.time);

            diag.printTestNumber();

            diag.recordDamage(component.damage);
            diag.recordPrice(component.price);
            diag.recordTime(component.time);

            if(component.time == 1.0) {
                listOfComponentTime.add(Time.FULLDAY);
            }
            else if(component.time == 0.5) {
                listOfComponentTime.add(Time.HALFDAY);
            }
            else if(component.time == 0.0) {
                listOfComponentTime.add(Time.ZERODAY);
            }

            SHOP_LOGGER.info(component.toString());
            System.out.println();
        }
        //3rd parameter variable: Use ObjIntConsumer<T> to check the List for any components
        //that are still working, i.e. require no payment
        diag.listOfStats(unsortedNames, lstStat, (t, value) -> {
            if(t.contains(value)) {
                SHOP_LOGGER.info("Among the diagnosed components, at least one requires no fixing:");
            }
        });

        //print list of each individual price, damage, and time (minus 0.0 time)
        List<LinkedListCustom> collectPrice = diag.linkedlistPrice.stream().sorted().collect(Collectors.toList());
        SHOP_LOGGER.info("List of Cost Values:" + collectPrice);
        List<Double> collectDamage = listOfComponents.stream().sorted().map(Component::getDamage).collect(Collectors.toList());
        SHOP_LOGGER.info("List of Damage Dealt:" + collectDamage);
        List<Time> collectTime = listOfComponentTime.stream().sorted().filter(t -> t.getTime() > 0.0).
                                        collect(Collectors.toList());
        SHOP_LOGGER.info("List of Time Needed:" + collectTime);

        //print total price and time
        printPriceAndTime(t -> (int)(t*1), p -> p);
    }

    public List<Component> registerComponent(Component components) {
        listOfComponents.add(components);
        return listOfComponents;
    }

}
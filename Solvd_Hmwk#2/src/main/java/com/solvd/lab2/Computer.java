package computerrepairservice;

//import java.util.Properties;
import computerrepairservice.exception.ComputerNotFoundException;
import computerrepairservice.interfaces.InitVerify;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Computer implements InitVerify {
    protected double weight;
    private int ramSize;
    private int diskSize;
    //possible to use PowerUnit in place of String to call propeties from PowerUnit class
    public String power;
    
    protected static final Logger COMPUTER_LOGGER = LogManager.getLogger();
    private Logger logger = COMPUTER_LOGGER;

    protected Logger getLogger() {
        return logger;
    }

    protected void setLogger(Logger logger) {
        this.logger = logger;
    }
    
    public int getRamSize() {
        return ramSize;
    }
    
    public void setRamSize(int ramSize) {
        this.ramSize = ramSize;
    }
    
    public int getDiskSize() {
        return diskSize;
    }
    
    public void setDiskSize(int diskSize) {
        this.diskSize = diskSize;
    }

    public Computer(double weight, int ramSize, int diskSize) throws ComputerNotFoundException {
        //if no viable computer is empty
        if(weight == 0.0 || (diskSize == 0 && ramSize == 0)) {
            throw new ComputerNotFoundException("No computer found to be diagnosed!");
        } else {
            this.weight = weight;
            this.ramSize = ramSize;
            this.diskSize = diskSize;
        }
    }
    
    //need default constructor to create constructors in HomeCom and Laptop
    //classes with the one variable in the parameter
    public Computer() {}
    
    @Override public void printComputerInfo() {
        if(weight < 5.0) {
            System.out.println("Your Laptop contains " + ramSize + "GB of RAM and " + diskSize + "GB of storage.");
        }
        else if(weight >= 5.0 && weight <= 10.0) {
            System.out.println("Your Computer Monitor contains " + ramSize + "GB of RAM and " + diskSize + "GB of storage.");
        }
        else {
            System.out.println("Invalid specification of computer model!!");
        }
    }
    
    //ask if the computer initially powers on.
    //should only be initialized in superclass.
    final void powerOnOff() {
        System.out.println("Does your computer turn on?");
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        power = myObj.nextLine();
        while(!power.equals("Yes") || !power.equals("No")) {
            System.out.println("Invalid input! Please type answer again.");
            power = myObj.nextLine();
            System.out.println();
        }
        System.out.println();

    }
    
    /*public void validComputer() {
        logger.error(e.getMessage());
        System.exit(1);
    }*/
}
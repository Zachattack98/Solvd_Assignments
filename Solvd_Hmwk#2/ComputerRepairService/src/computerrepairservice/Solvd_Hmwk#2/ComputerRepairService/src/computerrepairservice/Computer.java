package computerrepairservice;

//import java.util.Properties;

public class Computer {
    private double weight;
    private int ramSize;
    private int diskSize;
    protected String power;
    
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
    
    public double getWeight() {
        return weight;
    }
    
    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public void Computer(double weight, int ramSize, int diskSize) {
        this.weight = weight;
        this.ramSize = ramSize;
        this.diskSize = diskSize;
    }
    
    public void printComputerInfo(double weight, int ramSize, int diskSize) {
        if(weight < 5.0) {
            System.out.println("Your Laptop contains " + ramSize + "GB of RAM and " + diskSize + "GB of storage.");
        }
        else if(weight >= 5.0 && weight <= 10.0) {
            System.out.println("Your Laptop contains " + ramSize + "GB of RAM and " + diskSize + "GB of storage.");
        }
        else {
            System.out.println("Invalid specification of computer model!!");
        }
    }
    
    public void isWorking(String power) {
        if(power.equals("Yes")) {
            System.out.println("Your computer works just fine, thanks for stopping by!");
            System.out.println();
        }
        else if(power.equals("No")) {
            System.out.println("Please tell me what problems you are having.");
            System.out.println();
        }
    }
}

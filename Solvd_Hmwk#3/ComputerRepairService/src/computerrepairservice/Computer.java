package computerrepairservice;

//import java.util.Properties;

import java.util.Scanner;


public class Computer {
    protected double weight;
    private int ramSize;
    private int diskSize;
    public String power;
    
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
            System.out.println("Your Computer Monitor contains " + ramSize + "GB of RAM and " + diskSize + "GB of storage.");
        }
        else {
            System.out.println("Invalid specification of computer model!!");
        }
    }
    
    public void powerOnOff() {
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
    
    /*@Override public boolean equals(Object o) {
    
        if (o == this) {
            return true;
        }
        if (!(o instanceof Shop)) {
            return false;
        }
             
        Computer c = (Computer) o;
        
        return power.equals(c.power);
    }*/
}

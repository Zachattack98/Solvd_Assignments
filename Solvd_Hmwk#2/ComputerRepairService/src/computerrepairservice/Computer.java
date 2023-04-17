package computerrepairservice;

//import java.util.Properties;

public class Computer {
    protected double weight;
    private int ramSize;
    private int diskSize;
    private String power;
    
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
    
    public String getPower() {
        return power;
    }
    
    public void setPower(String power) {
        this.power = power;
    }
    
    public void Computer(double weight, int ramSize, int diskSize) {
        this.weight = weight;
        this.ramSize = ramSize;
        this.diskSize = diskSize;
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

package computerrepairservice;

//import java.util.Properties;
import java.util.Scanner;

public class Laptop extends Computer {
    private boolean battery;
    public String Ldecision;
    
    public boolean getBattery() {
        return battery;
    }
    
    public void setBattery(boolean battery) {
        this.battery = battery;
    }
    
    public void Laptop(boolean battery) {
        this.battery = battery;
    }
    
    public void Lcharging (boolean battery, String Ldecision) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        
        if(!battery) {
            while(!Ldecision.equals("Yes") || !Ldecision.equals("No")) {
                System.out.println("Invalid input! Try again.");
                Ldecision = myObj.nextLine();
                System.out.println();
            }
            if(Ldecision.equals("Yes")) {
                System.out.println("Great, I'll proceed with the Diagnosis!");
                System.out.println();
            }
            else if(Ldecision.equals("No")) {
                System.out.println("Sorry, I cannot help you then!");
                System.out.println();
            }
        }
        else {
            System.out.println("Great, I'll proceed with the Diagnosis!");
            System.out.println();
        }
    }
}

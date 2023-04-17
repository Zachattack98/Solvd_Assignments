package computerrepairservice;

//import java.util.Properties;
import java.util.Scanner;

public class Laptop extends Computer {
    private boolean battery;
    
    public boolean getBattery() {
        return battery;
    }
    
    public void setBattery(boolean battery) {
        this.battery = battery;
    }
    
    public void Laptop(boolean battery) {
        this.battery = battery;
    }
    
    public void Lcharging (boolean battery) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
    
        if(!battery) {
            System.out.println("It'll also cost you an extra $25 to replace. Are willing to do that?");
            String decision = myObj.nextLine();
            System.out.println();
            
            while(!decision.equals("Yes") && !decision.equals("No")) {
                System.out.println("Invalid input! Try again.");
                decision = myObj.nextLine();
                System.out.println();
            }
            if(decision.equals("Yes")) {
                System.out.println("Great, I'll proceed with the Diagnosis!");
                System.out.println();
            }
            else if(decision.equals("No")) {
                System.out.println("Sorry, I cannot help you then!");
                System.out.println();
            }
        }
    }
}

package computerrepairservice;

//import java.util.Properties;
import java.util.Scanner;

public class HomeCom {
    private boolean outlet;
    public String Hdecision;
    
    public boolean getOutlet() {
        return outlet;
    }
    
    public void setOutlet(boolean outlet) {
        this.outlet = outlet;
    }
    
    public void HomeCom(boolean outlet) {
        this.outlet = outlet;
    }
    
    public void Hcharging (boolean outlet, String Hdecision) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        
        if(!outlet) { 
            while(!Hdecision.equals("Yes") || !Hdecision.equals("No")) {
                System.out.println("Invalid input! Try again.");
                Hdecision = myObj.nextLine();
                System.out.println();
            }
            if(Hdecision.equals("Yes")) {
                System.out.println("Great, I'll proceed with the Diagnosis!");
                System.out.println();
            }
            else if(Hdecision.equals("No")) {
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

package computerrepairservice;

//import java.util.Properties;
import java.util.Scanner;

public class Laptop extends Computer {
    private String ldecision;
    
    public String getLdecision() {
        return ldecision;
    }
    
    public void setLdecision(String ldecision) {
        this.ldecision = ldecision;
    }
    
    public void Laptop(String ldecision) {
        this.ldecision = ldecision;
    }
    
    public void lcharging (String ldecision) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        
        if(power.equals("No")) {
            while(!ldecision.equals("Yes") || !ldecision.equals("No")) {
                System.out.println("Invalid input! Try again.");
                ldecision = myObj.nextLine();
                System.out.println();
            }
            if(ldecision.equals("Yes")) {
                System.out.println("Great, I'll proceed with the Diagnosis!");
                System.out.println();
            }
            else if(ldecision.equals("No")) {
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

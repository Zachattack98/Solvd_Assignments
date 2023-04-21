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
    
    public void lcharging () {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        
        if(weight < 5.0){
            System.out.println("Do you want me to take a closer look?");
            ldecision = myObj.nextLine();
            if(ldecision.equals("Yes")) {
                System.out.println("Great, I'll proceed with the Diagnosis!");
                System.out.println();
            }
            else if(ldecision.equals("No")) {
                System.out.println("Alright, thanks for stopping by!");
            }
        }
    }
}

package computerrepairservice;

//import java.util.Properties;
import java.util.Scanner;

public class HomeCom extends Computer {
    private String hdecision;
    
    public String getHdecision() {
        return hdecision;
    }
    
    public void setHdecision(String hdecision) {
        this.hdecision = hdecision;
    }
    
    public void HomeCom(String hdecision) {
        this.hdecision = hdecision;
    }
    
    public void hpower (String hdecision) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        
        if(power.equals("No")) {
            while(!hdecision.equals("Yes") || !hdecision.equals("No")) {
                System.out.println("Invalid input! Try again.");
                hdecision = myObj.nextLine();
                System.out.println();
            }
            if(hdecision.equals("Yes")) {
                System.out.println("Great, I'll proceed with the Diagnosis!");
                System.out.println();
            }
            else if(hdecision.equals("No")) {
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

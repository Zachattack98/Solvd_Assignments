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
    
    public void hpower () {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        
        if(weight >= 5.0 && weight <= 10.0){
            System.out.println("Do you want me to take a closer look?");
            hdecision = myObj.nextLine();
            if(hdecision.equals("Yes")) {
                System.out.println("Great, I'll proceed with the Diagnosis!");
                System.out.println();
            }
            else if(hdecision.equals("No")) {
                System.out.println("Alright, thanks for stopping by!");
            }
        }
    }
}

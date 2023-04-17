package computerrepairservice;

//import java.util.Properties;
import java.util.Scanner;

public class HomeCom {
    private boolean outlet;
    
    public boolean getOutlet() {
        return outlet;
    }
    
    public void setOutlet(boolean outlet) {
        this.outlet = outlet;
    }
    
    public void HomeCom(boolean outlet) {
        this.outlet = outlet;
    }
    
    public void Hcharging (boolean outlet) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
    
        if(!outlet) {
            System.out.println("It'll also cost you an extra $30 to replace. Are willing to do that?");
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

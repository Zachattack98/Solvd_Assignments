package computerrepairservice;

//import java.util.Properties;
import java.util.Scanner;
import computerrepairservice.exception.ComputerNotFoundException;
import computerrepairservice.interfaces.InitDiagnosis;

public class HomeCom extends Computer implements InitDiagnosis {
    private String hdecision;
    
    public String getHdecision() {
        return hdecision;
    }
    
    public void setHdecision(String hdecision) {
        this.hdecision = hdecision;
    }
    
    public HomeCom(double weight, int ramSize, int diskSize, String hdecision) throws ComputerNotFoundException {
        super(weight, ramSize, diskSize); //must be first statement in constructor
        //if no viable computer is empty
        if(weight == 0.0 || (diskSize == 0 && ramSize == 0)) {
            throw new ComputerNotFoundException("No computer found to be diagnosed!");
        } else {
            this.weight = weight;
        }
        this.hdecision = hdecision;
    }
    
    public HomeCom(String hdecision) {
        this.hdecision = hdecision;
    }
    
    @Override 
    public void proceed() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        
        //if it is a home computer and powers on, ask customer if you can proceed with diagnosis
        if(weight >= 5.0 && weight <= 10.0 && power.equals("No")){
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

package computerrepairservice;

//import java.util.Properties;

interface NumberComponent {
    //total number of components
    public static int NUM_COMPONENTS = 5;
}

public class Diagnostic implements NumberComponent{
    public int status; //0 == good; 1 == repair; 2 == replace
    
    public Diagnostic(int status) {
        this.status = status;
    }
    
    public Diagnostic() {}
    
    public void result(String component, int status) {
        switch(status) {
            case(1):
                System.out.println(component + " needs to be repaired.");
                break;
            case(2):
                System.out.println(component + " needs to be replaced.");
                break;
            case(3):
                System.out.println(component + " is working just fine.");
                break;
            default:
                break;
        }
    }
    
    @Override public String toString() {
        return("Total number of different components that will be analyzed: " + NUM_COMPONENTS);
    }
    
    //make sure there is a computer present in order to perform any diagnosis
    public void computerExists() {
        try {
            Class.forName("Computer");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

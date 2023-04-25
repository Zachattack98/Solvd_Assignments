package computerrepairservice;

//import java.util.Properties;

interface NumberComponent {
    //total number of components
    public static int NUM_COMPONENTS = 5;
}

public class Diagnostic implements NumberComponent{
    public int status; //0 == good; 1 == repair; 2 == replace
    public double time; //number of days the repairs will take
    
    public Diagnostic(double time) {
        this.time = time;
    }
    
    public Diagnostic() {}
    
    public void result(String component, int status) {
        switch(status) {
            case(1):
                System.out.println(component + " needs to be repaired.");
                time = 0.5;
                break;
            case(2):
                System.out.println(component + " needs to be replaced.");
                time = 1.0; //let's say each item adds a day to the shipping
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
}

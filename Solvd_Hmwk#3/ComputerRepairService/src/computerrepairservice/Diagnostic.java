package computerrepairservice;

//import java.util.Properties;

public class Diagnostic {
    public String nameComponent;
    public int numComponents;
    public int broken; //0 == good; 1 == repair; 2 == replace
    protected double time; //number of days the repairs will take
    
    public void Diagnostic(String nameComponent) {
        this.nameComponent = nameComponent;
    }
    
    public void result(String component, int broken) {
        switch(broken) {
            case(1):
                System.out.println(component + " needs to be repaired.");
                time += 0.5;
                break;
            case(2):
                System.out.println(component + " needs to be replaced.");
                time += 1.0; //let's say each item adds a day to the shipping
                break;
            default:
                System.out.println(component + " is working just fine.");
                break;
        }
    }
    
    @Override public String toString() {
        return("Total number of different components that will be analyzed: " + numComponents);
    }
}

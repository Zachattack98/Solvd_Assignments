package computerrepairservice;

//import java.util.Properties;

public class Diagnostic {
    public String component;
    public int broken; //0 == good; 1 == repair; 2 == replace
    
    public void Diagnostic(String component) {
        this.component = component;
    }
    
    public void result(String component, int broken) {
        switch(broken) {
            case(1):
                System.out.println(component + " needs to be repaired.");
                break;
            case(2):
                System.out.println(component + " needs to be replaced.");
                break;
            default:
                System.out.println(component + " is working just fine.");
                break;
        }
    }
}

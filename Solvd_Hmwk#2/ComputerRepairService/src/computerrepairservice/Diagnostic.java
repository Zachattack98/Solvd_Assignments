package computerrepairservice;

//import java.util.Properties;

public class Diagnostic {
    public String array;
    public boolean broken; //1 == repair; 0 == replace
    
    public void Diagnostic(String array, boolean broken) {
        this.array = array;
        this.broken = broken;
    }
    
    public void result(String array, boolean broken) {
        if(broken){
            System.out.println(array + " needs to be repaired.");
        }
        else {
            System.out.println(array + " needs to be replaced.");
        }
    }
}

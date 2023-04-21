package computerrepairservice;

//import java.util.Properties;

public class TotalCost extends Diagnostic {
    public int individualPrice;
    public int total;
    
    public void TotalCost(int individualPrice, int total) {
        this.individualPrice = individualPrice;
        this.total = total;
    }
    
    public double calaculateCost(int individualPrice, int total) {
        total += individualPrice;
        return total;
    }
    
    @Override public String toString() {
        return("Total cost for repairs: $" + total + " and will take " + time + " days to finish!");
    }
}

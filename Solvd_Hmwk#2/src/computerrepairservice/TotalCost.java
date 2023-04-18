package computerrepairservice;

//import java.util.Properties;

public class TotalCost {
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
}

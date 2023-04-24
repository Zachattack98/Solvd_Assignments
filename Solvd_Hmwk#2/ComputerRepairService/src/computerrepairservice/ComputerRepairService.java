package computerrepairservice;

public class ComputerRepairService {

    public static void main(String[] args) {
        ServiceShop techShop = new ServiceShop("Gyro Tech Computer Repair Service", "Irvine, CA");
        System.out.println(techShop.toString());
        System.out.println();
        
        Computer comp = new Computer(4.3, 16, 4);
        comp.powerOnOff();
        
        if(comp.power.equals("No")) {
            Laptop lap = new Laptop("Yes");
            lap.proceed();
            HomeCom home = new HomeCom("Yes");
            home.proceed();
            
            Diagnostic diag = new Diagnostic(0.0); //start working time at zero
            diag.toString();
        
            Screen screen = new Screen("LCD Screen", 34.7, 36);
            HardDrive hd = new HardDrive("Hard Drive", 87.4, 20);
            AdapterUSB adapt = new AdapterUSB("USB Adapter(s)", 12.9, 1);
            PowerUnit punit = new PowerUnit("Power Supply Unit", 65.1, 70);
            Fan fan = new Fan("Cooling Fan", 95.0, 25);
            Component[] components = {screen, hd, adapt, punit, fan};
        
            int total = 0;
            //use for-each loop to implement calculatePrice() method in each sub class of Component()
            for(Component component: components) {
                total += component.calculatePrice();
                component.toString();
                System.out.println();
            }
        
            //output overall cost and time it will take
            System.out.println("Total cost for repairs: $" + total + " and will take " + diag.time + " days to finish!");
            System.out.println();
        }
        else {
            System.out.println("Alright, thanks for stopping by!");
            System.out.println();
        }
    }

}
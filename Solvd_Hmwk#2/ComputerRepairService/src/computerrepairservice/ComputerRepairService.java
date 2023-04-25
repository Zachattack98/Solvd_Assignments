package computerrepairservice;

public class ComputerRepairService {

    public static int testNumber = 0;
    //print which diagnosis you are performing out of the total.
    //must be declared inside same class to call without object.
    public static void printTestNumber() {
        testNumber++;
        System.out.println("Diagnosis Test #" + testNumber + ":");
    }
    
    public static void main(String[] args) {
        ServiceShop techShop = new ServiceShop("Gyro Tech Computer Repair Service", "Irvine, CA");
        System.out.println(techShop.toString());
        System.out.println();
        
        Computer comp = new Computer(4.3, 16, 4);
        comp.printComputerInfo();
        System.out.println();
        //Error: Yes, No, and everything ele keep turning out invalid
        //comp.powerOnOff();
        
        //if(comp.power.equals("No")) {
            //Laptop lap = new Laptop("Yes");
            //lap.proceed();
            //HomeCom home = new HomeCom("Yes");
            //home.proceed();
            
            Diagnostic diag = new Diagnostic(0.0);
            System.out.println(diag.toString());
        
            Screen screen = new Screen("LCD Screen", 34.7, 36);
            HardDrive hd = new HardDrive("Hard Drive", 87.4, 20);
            AdapterUSB adapt = new AdapterUSB("USB Adapter(s)", 6.2, 1);
            PowerUnit punit = new PowerUnit("Power Supply Unit", 1.9, 70);
            Fan fan = new Fan("Cooling Fan", 54.0, 25);
            Component[] components = {screen, hd, adapt, punit, fan}; //combine all objects together into a new array
        
            int totalCost = 0;
            //double totalTime = 0.0;
            //use for-each loop to implement calculatePrice() method in each sub class of Component()
            for(Component component: components) {
                printTestNumber();
                totalCost += component.calculatePrice();
                //totalTime += diag.time;
                System.out.println(component.toString());
                System.out.println();
            }
        
            //output overall cost and time it will take.
            //Error: time keeps reseting to 0.0, but does assign in the switch case in result()
            System.out.println("Total cost for repairs: $" + totalCost + ", and will take " + diag.time + " days to finish!");
            System.out.println();
        //}
        /*else {
            System.out.println("Alright, thanks for stopping by!");
            System.out.println();
        }*/
    }

}
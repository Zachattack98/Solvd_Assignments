package computerrepairservice;

import java.util.Scanner;

public class ComputerRepairService {

    public static void main(String[] args) {
        String arrayComponents[] = {"LCD Screen", "Hard Drive", "USB Adapter(s)", "Power Supply Unit", "Cooling Fan"};
        double dmg[] = {34.7, 87.4, 12.9, 65.1, 95.0};
        int specs[] = {36, 20, 1, 70, 25};
        
        Shop techShop = new Shop();
        techShop.setName("Gyro Tech Computer Repair Service");
        techShop.setLocation("Irvine, CA");
        techShop.printShop();

        Components parts = new Components();
        parts.printComponents(arrayComponents.length);
        
        Computer comp = new Computer();
        comp.setDiskSize(4); //GB
        comp.setRamSize(16); //GB
        comp.setWeight(4.3); //lbs
        
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Does your computer turn on?");
        String response = myObj.nextLine();
        while(!response.equals("Yes") || !response.equals("No")) {
            System.out.println("Does your computer turn on?");
            response = myObj.nextLine();
            System.out.println();
        }
        System.out.println();
        
        TotalCost cost = new TotalCost();
        int total = 0;
        boolean source = true;
        if(comp.getWeight() < 5.0){ //Laptop
            Laptop lap = new Laptop();
            System.out.println("It'll also cost you an extra $25 to replace. Are willing to do that?");
            lap.Ldecision = myObj.nextLine();
            System.out.println();
            
            lap.Lcharging(source, lap.Ldecision);
            if(lap.Ldecision.equals("Yes")){
                cost.TotalCost(25, total);
                cost.calaculateCost(25, total);
            }
        }
        else if(comp.getWeight() >= 5.0 && comp.getWeight() <= 10.0){ //Home Computer
            HomeCom home = new HomeCom();
            System.out.println("It'll also cost you an extra $30 to replace. Are willing to do that?");
            home.Hdecision = myObj.nextLine();
            System.out.println();
            
           home.Hcharging(source, home.Hdecision);
           if(home.Hdecision.equals("Yes")){
                cost.TotalCost(30, total);
                cost.calaculateCost(25, total);
            }
        }
        System.out.println();
            
        if(response.equals("Yes")) {
            int tempstat = 0;
            int tempprice = 0;
            Diagnostic diag = new Diagnostic();
            
            for(int i = 0; i < arrayComponents.length; i++) {
                //check both methods for repairable and replaceable components
                if(dmg[i] <= 60.0) {
                    tempstat = 1;
                }
                else if(dmg[i] > 60.0 && dmg[i] <= 90.0) {
                    tempstat = 2;
                }
                else {
                    tempstat = 0;
                }
                diag.result(arrayComponents[i], tempstat);
                
                switch(i+1) {
                    case(1):
                        Screen myScreen = new Screen();
                        myScreen.setSize(specs[i]);
                        if(tempstat == 1){
                            cost.calaculateCost(myScreen.screenPrice(), total);
                        }
                        else if(tempstat == 2) {
                            cost.calaculateCost(70, total); //$70 to replace
                        }
                        break;
                    case(2):
                        HardDrive myHardDrive = new HardDrive();
                        myHardDrive.setBytes(specs[i]);
                        if(tempstat == 1){
                            cost.calaculateCost(myHardDrive.harddrivePrice(), total);
                        }
                        else if(tempstat == 2) {
                            cost.calaculateCost(60, total);
                        }
                        break;
                     case(3):
                        AdapterUSB myAdapter = new AdapterUSB();
                        myAdapter.setAdapter(specs[i]);
                        if(tempstat == 1) {
                            cost.calaculateCost(myAdapter.adapterPrice(), total);
                        }
                        else if(tempstat == 2) {
                            cost.calaculateCost((myAdapter.getNumComponents()* 20), total);
                        }
                        break;
                    case(4):
                        PowerUnit myPower = new PowerUnit();
                        myPower.setWattage(specs[i]);
                        if(tempstat == 1){
                            cost.calaculateCost(myPower.powerunitPrice(), total);
                        }
                        else if(tempstat == 2) {
                            cost.calaculateCost(75, total);
                        }
                        break;
                    case(5):
                        Fan myFan = new Fan();
                        myFan.setFan(specs[i]);
                        if(tempstat == 1){
                            cost.calaculateCost(myFan.fanPrice(), total);
                        }
                        else if(tempstat == 2) {
                            cost.calaculateCost(40, total);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        System.out.println();
        System.out.println("Total cost for repairs: $" + cost.total);
        System.out.println();
    }

}
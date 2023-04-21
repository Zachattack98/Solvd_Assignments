package computerrepairservice;

import java.util.Scanner;

public class ComputerRepairService {

    public static void main(String[] args) {
        String arrayComponents[] = {"LCD Screen", "Hard Drive", "USB Adapters", "Power Supply Unit", "Cooling Fan"};
        double dmg[] = {34.7, 87.4, 12.9, 65.1, 95.0};
        int specs[] = {36, 20, 1, 70, 25};
        
        Shop techShop = new Shop();
        techShop.setName("Gyro Tech Computer Repair Service");
        techShop.setLocation("Irvine, CA");
        System.out.println(techShop.toString());
        System.out.println();
        
        Computer comp = new Computer();
        comp.setDiskSize(4); //GB
        comp.setRamSize(16); //GB
        comp.setWeight(4.3); //lbs
        
        comp.powerOnOff();
         
        Laptop lap = new Laptop();
        lap.lcharging();
        HomeCom home = new HomeCom();
        home.hpower();
            
        TotalCost cost = new TotalCost();
        int total = cost.total;
            
        if(comp.power.equals("Yes")) {
            int tempstat = 0;
            Diagnostic diag = new Diagnostic();

            diag.numComponents = arrayComponents.length;
            System.out.println(diag.toString());
            System.out.println();
            System.out.println();
            for(int i = 0; i < arrayComponents.length; i++) {
                switch(i+1) {
                    case(1):
                        Screen myScreen = new Screen();
                        tempstat = myScreen.isRepairable(dmg[i], tempstat);
                        tempstat = myScreen.isReplaceable(dmg[i], tempstat);
                        if(tempstat == 1){
                            cost.calaculateCost(myScreen.screenPrice(), total);
                        }
                        else if(tempstat == 2) {
                            cost.calaculateCost(70, total); //$70 to replace
                        }
                        break;
                    case(2):
                        HardDrive myHardDrive = new HardDrive();
                        tempstat = myHardDrive.isRepairable(dmg[i], tempstat);
                        tempstat = myHardDrive.isReplaceable(dmg[i], tempstat);
                        if(tempstat == 1){
                            cost.calaculateCost(myHardDrive.harddrivePrice(), total);
                        }
                        else if(tempstat == 2) {
                            cost.calaculateCost(60, total);
                        }
                        break;
                     case(3):
                        AdapterUSB myAdapter = new AdapterUSB();
                        tempstat = myAdapter.isRepairable(dmg[i], tempstat);
                        tempstat = myAdapter.isReplaceable(dmg[i], tempstat);
                        if(tempstat == 1) {
                            cost.calaculateCost(myAdapter.adapterPrice(), total);
                        }
                        else if(tempstat == 2) {
                            cost.calaculateCost((specs[i] * 20), total);
                        }
                        break;
                    case(4):
                        PowerUnit myPower = new PowerUnit();
                        tempstat = myPower.isRepairable(dmg[i], tempstat);
                        tempstat = myPower.isReplaceable(dmg[i], tempstat);
                        if(tempstat == 1){
                            cost.calaculateCost(myPower.powerunitPrice(), total);
                        }
                        else if(tempstat == 2) {
                            cost.calaculateCost(75, total);
                        }
                        break;
                    case(5):
                        Fan myFan = new Fan();
                        tempstat = myFan.isRepairable(dmg[i], tempstat);
                        tempstat = myFan.isReplaceable(dmg[i], tempstat);
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
                
                diag.result(arrayComponents[i], tempstat);
                
            }
        }
        System.out.println();
        System.out.println(cost.toString());
        System.out.println();
    }

}
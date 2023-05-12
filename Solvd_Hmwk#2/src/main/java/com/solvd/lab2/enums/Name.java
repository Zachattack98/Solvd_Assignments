package computerrepairservice.enums;

public enum Name {
    SCREEN,
    HARDDRIVE,
    ADAPTERS,
    POWERUNIT,
    COOLINGFAN;
    
    /*SCREEN("LCD Screen"),
    HDD("Hard Drive"),
    USB ("USB Adapters"),
    POWERUNIT ("Power Unit"),
    COOLINGFAN ("Cooling Fan");*/
    
    //print all constants individually
    private Name() {
        System.out.println("List of components that will be tested:");
        System.out.println();
    }
    
    public void nameInfo() {
        System.out.println("All of these components can be tested in home computer or laptop.");
        System.out.println();
    }
}

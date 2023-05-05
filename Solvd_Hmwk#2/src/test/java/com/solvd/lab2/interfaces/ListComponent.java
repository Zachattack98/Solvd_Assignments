package computerrepairservice.interfaces;

public interface ListComponent {
    //static in interface must have method body
    public static void writeComponentListException() {};
    public static void readComponentListException() {};
    
    //Note: hash set starts at the end of the string, so list the components in ascending order
    //public static String NAMES_OF_COMPONENTS = "Fan Power USB HardDrive Screen";
}

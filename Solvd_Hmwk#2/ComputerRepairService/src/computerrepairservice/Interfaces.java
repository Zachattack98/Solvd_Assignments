package computerrepairservice;

/*Computer class*/
//verify the basics of the computer: GB storage and power
interface InitVerify {
    //determine if computer initially turns on 
    //public void powerOnOff();
    
    //print the type of computer and data storage
    public void printComputerInfo();
}

interface InitDiagnosis {
    //proceed with diagnosis if power is off
    public void proceed();
}

/*Component class*/
//cannot declare public because the interface would require its own .java file
interface Stat {
    //unchangeable static variables for determing repair/replacement status
    public final static int STATUS_REPAIR = 1;
    public final static int STATUS_REPLACE = 2;
    public final static int STATUS_WORKING = 3;
}

interface ListComponent {
    //static in interface must have method body
    public static void writeComponentListException() {};
    public static void readComponentListException() {};
    
    //Note: hash set starts at the end of the string, so list the components in ascending order
    public static String NAMES_OF_COMPONENTS = "Fan Power USB HardDrive Screen";
}

/*Diagnostic class*/
interface NumberComponent {
    //total number of components
    public static int NUM_COMPONENTS = 5;
}
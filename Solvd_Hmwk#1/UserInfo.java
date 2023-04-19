public class UserInfo {
    
    public static void main(String[] args) {
        if(args.length == 0) {
            System.out.println("Sorry, I don't see snything posted!");
        }
        else if(args.length < 3) {
            System.out.println("Sorry, not enough information provided!");
        }
        else if(args.length > 3) {
            System.out.println("Sorry, too much information provided!");
        }
        else {
        System.out.println("Hello World! My name is " + args[0] + ", I live in " + args[1] + " and work as a(n) " + args[2] + ".");
        }
    }
    
}

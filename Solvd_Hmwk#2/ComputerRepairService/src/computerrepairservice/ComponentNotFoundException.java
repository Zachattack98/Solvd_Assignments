package computerrepairservice;

public class ComponentNotFoundException extends Exception {
    public ComponentNotFoundException (String message) {
        super(message);
    }
}
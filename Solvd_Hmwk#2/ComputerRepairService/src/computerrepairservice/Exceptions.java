package computerrepairservice;

public class Exceptions {
    public class ShopNotFoundException extends RuntimeException {
        public ShopNotFoundException (String message) {
            super(message);
        }
    }

    public class PriceInvalidException extends Exception {
        public PriceInvalidException (String message) {
            super(message);
        }
    }

    public class DamageRangeInvalidException extends Exception {
        public DamageRangeInvalidException (String message) {
            super(message);
        }
    }

    public class ComputerNotFoundException extends RuntimeException {
        public ComputerNotFoundException (String message) {
            super(message);
        }
    }

    public class ComponentNotFoundException extends RuntimeException {
        public ComponentNotFoundException (String message) {
            super(message);
        }
    }
}
package computerrepairservice.exception;

public class PriceInvalidException extends RuntimeException {
    public PriceInvalidException (String message) {
        super(message);
    }
}

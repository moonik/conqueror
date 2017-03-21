package conqueror.shop.exceptions;

public class NotYourCastleException extends RuntimeException {
    public NotYourCastleException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotYourCastleException(String message) {
        super(message);
    }

    public NotYourCastleException() {
    }
}
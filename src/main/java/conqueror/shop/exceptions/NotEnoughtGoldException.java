package conqueror.shop.exceptions;

public class NotEnoughtGoldException extends RuntimeException {
    public NotEnoughtGoldException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughtGoldException(String message) {
        super(message);
    }

    public NotEnoughtGoldException() {
    }
}


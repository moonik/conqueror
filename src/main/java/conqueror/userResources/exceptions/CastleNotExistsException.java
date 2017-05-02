package conqueror.userResources.exceptions;

public class CastleNotExistsException extends RuntimeException {
    public CastleNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CastleNotExistsException(String message) {
        super(message);
    }

    public CastleNotExistsException() {
    }
}
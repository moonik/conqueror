package conqueror.castle.exceptions;

public class CastleException extends RuntimeException {
    public CastleException(String message, Throwable cause) {
        super(message, cause);
    }

    public CastleException(String message) {
        super(message);
    }

    public CastleException() {
    }
}
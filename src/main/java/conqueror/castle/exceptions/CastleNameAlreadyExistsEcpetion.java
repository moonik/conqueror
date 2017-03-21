package conqueror.castle.exceptions;

public class CastleNameAlreadyExistsEcpetion extends RuntimeException {
    public CastleNameAlreadyExistsEcpetion(String message, Throwable cause) {
        super(message, cause);
    }

    public CastleNameAlreadyExistsEcpetion(String message) {
        super(message);
    }

    public CastleNameAlreadyExistsEcpetion() {
    }
}

package conqueror.army.exceptions;

public class WrongAmountOfArmyException extends RuntimeException {
    public WrongAmountOfArmyException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongAmountOfArmyException(String message) {
        super(message);
    }

    public WrongAmountOfArmyException() {
    }
}
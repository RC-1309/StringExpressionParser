package expression.exceptions;

public class MissingArgumentException extends IllegalArgumentException {
    MissingArgumentException(String message) {
        super(message);
    }
}

package expression.exceptions;

public class IncorrectOperandException extends IllegalArgumentException {
    IncorrectOperandException(String message) {
        super(message);
    }
}

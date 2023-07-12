package expression.exceptions;

public class IncorrectOperandException extends IllegalArgumentException {
    public IncorrectOperandException(String message) {
        super(message);
    }
}

package expression.exceptions;

public interface CheckExceptions {
    static int mul(int a, int b) {
        final int result = a * b;
        if (a == 0 || b == 0) {
            return result;
        }
        if (result / a != b || result / b != a) {
            throw new OverflowException("Overflow in multiply: " + a + " " + b);
        }
        return result;
    }
}

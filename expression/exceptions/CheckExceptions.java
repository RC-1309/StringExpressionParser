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

    static int add(int a, int b) {
        int result = a + b;
        if (((a ^ result) & (b ^ result)) < 0) {
            throw new OverflowException("Overflow in sum: " + a + " " + b);
        }
        return result;
    }

    static int div(int a, int b) {
        if (b == 0) {
            throw new DivisionByZero("division by zero");
        } else {
            if (a == Integer.MIN_VALUE && b == -1) {
                throw new OverflowException("Overflow in divide: " + a + " " + b);
            } else {
                return a / b;
            }
        }
    }

    static int sub(int a, int b) {
        if (b > 0 && a < Integer.MIN_VALUE + b || b < 0 && a > Integer.MAX_VALUE + b) {
            throw new OverflowException("Overflow in subtract: " + a + " " + b);
        }
        return a - b;
    }

    static int neg(int a) {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException("overflow in negate");
        } else {
            return -a;
        }
    }

    static int abs(int a) {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException("overflow in abs");
        } else {
            return a < 0 ? -a : a;
        }
    }

    static int square(int a) {
        final int result = a * a;
        if (a == 0) {
            return result;
        }
        if (result / a != a) {
            throw new OverflowException("Overflow in square: " + a);
        }
        return result;
    }

    static int mod(int a, int b) {
        if (b == 0) {
            throw new DivisionByZero("division by zero");
        }
        return a % b;
    }
}

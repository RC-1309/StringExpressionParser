package expression.exceptions;

import expression.ExtendedExpression;
import expression.Reverse;

public class CheckReverse extends Reverse {
    public CheckReverse(ExtendedExpression expression) {
        super(expression);
    }

    @Override
    protected int calculate(int a) {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException("Overflow in reverse: " + a);
        }
        if (a == 0) {
            return a;
        }
        StringBuilder sb = new StringBuilder();
        if (a < 0) {
            sb.append('-');
            a = -a;
        }
        while (a > 0) {
            sb.append(a % 10);
            a /= 10;
        }
        try {
            return Integer.parseInt(sb.toString());
        } catch (NumberFormatException e) {
            throw new OverflowException("Overflow in reverse: " + sb);
        }
    }
}

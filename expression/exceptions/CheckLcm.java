package expression.exceptions;

import expression.ExtendedExpression;
import expression.Lcm;

public class CheckLcm extends Lcm {
    public CheckLcm(ExtendedExpression firstExpression, ExtendedExpression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    protected int calculate(int a, int b) {
        int g = gcd(abs(a), abs(b));
        if (g == 0) {
            return 0;
        }
        if (a == Integer.MIN_VALUE && g == -1) {
            throw new OverflowException("Overflow in Lcm: " + a + " " + b);
        }
        return CheckExceptions.mul(a / g, b);
    }
}

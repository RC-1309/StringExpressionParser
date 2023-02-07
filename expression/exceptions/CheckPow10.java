package expression.exceptions;

import expression.ExtendedExpression;
import expression.Pow10;

public class CheckPow10 extends Pow10 {
    public CheckPow10(ExtendedExpression expression) {
        super(expression);
    }

    @Override
    protected int calculate(int a) {
        if (a < 0) {
            throw new NegatePowException("Negate in pow: " + a);
        } else if (a > 9) {
            throw new OverflowException("Overflow in pow: " + a);
        } else {
            return super.calculate(a);
        }
    }
}

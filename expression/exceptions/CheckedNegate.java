package expression.exceptions;

import expression.*;

public class CheckedNegate extends Minus {

    public CheckedNegate(ExtendedExpression expression) {
        super(expression);
    }

    @Override
    protected int calculate(int a) {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException("overflow in negate");
        } else {
            return super.calculate(a);
        }
    }
}

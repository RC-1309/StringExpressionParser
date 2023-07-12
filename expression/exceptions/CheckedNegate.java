package expression.exceptions;

import expression.ExtendedExpression;
import expression.Minus;

public class CheckedNegate extends Minus {

    public CheckedNegate(ExtendedExpression expression) {
        super(expression);
    }

    @Override
    protected int calculate(int a) {
        return CheckExceptions.neg(a);
    }
}

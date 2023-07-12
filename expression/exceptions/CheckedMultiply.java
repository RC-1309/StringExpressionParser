package expression.exceptions;

import expression.ExtendedExpression;
import expression.Multiply;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(ExtendedExpression firstExpression, ExtendedExpression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    protected int calculate(int a, int b) {
        return CheckExceptions.mul(a, b);
    }
}

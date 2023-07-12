package expression.exceptions;

import expression.Divide;
import expression.ExtendedExpression;

public class CheckedDivide extends Divide {
    public CheckedDivide(ExtendedExpression firstExpression, ExtendedExpression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    protected int calculate(int a, int b) {
        return CheckExceptions.div(a, b);
    }
}

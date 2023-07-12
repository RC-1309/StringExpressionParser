package expression.exceptions;

import expression.ExtendedExpression;
import expression.Subtract;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(ExtendedExpression firstExpression, ExtendedExpression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    protected int calculate(int a, int b) {
        return CheckExceptions.sub(a, b);
    }
}

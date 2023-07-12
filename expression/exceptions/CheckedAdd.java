package expression.exceptions;

import expression.Add;
import expression.ExtendedExpression;

public class CheckedAdd extends Add {
    public CheckedAdd(ExtendedExpression firstExpression, ExtendedExpression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    protected int calculate(int a, int b) {
        return CheckExceptions.add(a, b);
    }
}

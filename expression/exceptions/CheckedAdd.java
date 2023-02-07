package expression.exceptions;

import expression.Add;
import expression.ExtendedExpression;

public class CheckedAdd extends Add {
    public CheckedAdd(ExtendedExpression firstExpression, ExtendedExpression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    protected int calculate(int a, int b) {
        int result = a + b;
        if (((a ^ result) & (b ^ result)) < 0) {
            throw new OverflowException("Overflow in sum: " + a + " " + b);
        }
        return result;
    }
}

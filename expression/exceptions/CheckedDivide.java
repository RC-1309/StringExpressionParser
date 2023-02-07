package expression.exceptions;

import expression.Divide;
import expression.ExtendedExpression;

public class CheckedDivide extends Divide {
    public CheckedDivide(ExtendedExpression firstExpression, ExtendedExpression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    protected int calculate(int a, int b) {
        if (b == 0) {
            throw new DivisionByZero("division by zero");
        } else {
            if (a == Integer.MIN_VALUE && b == -1) {
                throw new OverflowException("Overflow in divide: " + a + " " + b);
            } else {
                return super.calculate(a, b);
            }
        }
    }
}

package expression.exceptions;

import expression.ExtendedExpression;
import expression.Log10;

public class CheckLog10 extends Log10 {
    public CheckLog10(ExtendedExpression expression) {
        super(expression);
    }

    @Override
    protected int calculate(int a) {
        if (a < 1) {
            throw new NumberFormatException("Wrong argument in log: " + a);
        } else {
            return super.calculate(a);
        }
    }
}

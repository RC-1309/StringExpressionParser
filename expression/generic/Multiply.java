package expression.generic;

import expression.Priority;

public class Multiply<T extends Number> extends BinaryOperation<T> {
    private static final String OPERATION = "*";

    public Multiply(ExtendedExpression<T> firstExpression, ExtendedExpression<T> secondExpression, Calculator<T> calculator) {
        super(firstExpression, secondExpression, calculator);
    }

    @Override
    public boolean getCommutative() {
        return true;
    }

    public String getOperation() {
        return OPERATION;
    }

    @Override
    public int getPriority() {
        return Priority.getMultiplyPriority();
    }

    @Override
    protected T calculate(T a, T b) {
        return calculator.mul(a, b);
    }
}

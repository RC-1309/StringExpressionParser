package expression.generic;

import expression.Priority;

public class Subtract<T extends Number> extends BinaryOperation<T> {
    private static final String OPERATION = "-";

    public Subtract(ExtendedExpression<T> firstExpression, ExtendedExpression<T> secondExpression, Calculator<T> calculator) {
        super(firstExpression, secondExpression, calculator);
    }

    @Override
    public boolean getCommutative() {
        return false;
    }

    public String getOperation() {
        return OPERATION;
    }

    @Override
    public int getPriority() {
        return Priority.getSubtractPriority();
    }

    @Override
    protected T calculate(T a, T b) {
        return calculator.sub(a, b);
    }
}

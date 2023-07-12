package expression.generic;

import expression.Priority;

public class Divide<T extends Number> extends BinaryOperation<T> {
    private static final String OPERATION = "/";

    public Divide(ExtendedExpression<T> firstExpression, ExtendedExpression<T> secondExpression,
                  Calculator<T> calculator) {
        super(firstExpression, secondExpression, calculator);
    }

    @Override
    public boolean getCommutative() {
        return false;
    }

    @Override
    public boolean specialCommutative() {
        return true;
    }

    public String getOperation() {
        return OPERATION;
    }

    @Override
    public int getPriority() {
        return Priority.getDividePriority();
    }

    @Override
    protected T calculate(T a, T b) {
        return calculator.div(a, b);
    }
}

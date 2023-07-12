package expression.generic;

import expression.Priority;

public class Add<T extends Number> extends BinaryOperation<T> {
    private static final String OPERATION = "+";

    public Add(ExtendedExpression<T> firstExpression, ExtendedExpression<T> secondExpression, Calculator<T> calculator) {
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
        return Priority.getAddPriority();
    }

    @Override
    protected T calculate(T a, T b) {
        return calculator.add(a, b);
    }
}

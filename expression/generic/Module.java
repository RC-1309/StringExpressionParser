package expression.generic;

import expression.Priority;

public class Module<T extends Number> extends BinaryOperation<T> {
    private static final String OPERATION = "mod";
    public Module(ExtendedExpression<T> firstExpression, ExtendedExpression<T> secondExpression, Calculator<T> calculator) {
        super(firstExpression, secondExpression, calculator);
    }

    @Override
    protected String getOperation() {
        return OPERATION;
    }

    @Override
    protected T calculate(T a, T b) {
        return calculator.mod(a, b);
    }

    @Override
    public int getPriority() {
        return Priority.getMultiplyPriority();
    }

    @Override
    public boolean getCommutative() {
        return false;
    }
}

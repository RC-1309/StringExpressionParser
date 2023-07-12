package expression.generic;

import expression.Priority;

public abstract class UnaryOperation<T extends Number> implements ExtendedExpression<T> {
    protected final ExtendedExpression<T> expression;
    protected final Calculator<T> calculator;

    public UnaryOperation(ExtendedExpression<T> expression, Calculator<T> calculator) {
        this.expression = expression;
        this.calculator = calculator;
    }

    protected abstract String getOperation();

    @Override
    public int getPriority() {
        return Priority.getUnaryOperationPriority();
    }

    @Override
    public boolean specialCommutative() {
        return false;
    }

    @Override
    public boolean getCommutative() {
        return false;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return calculate(expression.evaluate(x, y, z));
    }

    protected abstract T calculate(T a);

    @Override
    public String toString() {
        return getOperation() + "(" + expression.toString() + ")";
    }

    private void makeExpression(StringBuilder sb, String expression, boolean needsBrackets) {
        if (needsBrackets) {
            sb.append("(").append(expression).append(")");
        } else {
            sb.append(" ").append(expression);
        }
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder(getOperation());
        makeExpression(sb, expression.toMiniString(), expression.getPriority() > getPriority());
        return sb.toString();
    }

    @Override
    public boolean uniqueCommutative() {
        return false;
    }
}

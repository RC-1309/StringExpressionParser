package expression.generic;

import java.util.Objects;

public abstract class BinaryOperation<T extends Number> implements ExtendedExpression<T> {
    protected final ExtendedExpression<T> firstExpression;
    protected final ExtendedExpression<T> secondExpression;
    protected final Calculator<T> calculator;

    public BinaryOperation(ExtendedExpression<T> firstExpression, ExtendedExpression<T> secondExpression,
                           Calculator<T> calculator) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.calculator = calculator;
    }

    protected abstract String getOperation();

    @Override
    public boolean specialCommutative() {
        return false;
    }

    @Override
    public String toString() {
        return "(" + firstExpression.toString() + " " + getOperation() + " " + secondExpression.toString() + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstExpression, secondExpression, getClass());
    }

    private static void makeExpression(StringBuilder sb, String expression, boolean needsBrackets) {
        if (needsBrackets) {
            sb.append("(").append(expression).append(")");
        } else {
            sb.append(expression);
        }
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        makeExpression(sb, firstExpression.toMiniString(), firstExpression.getPriority() > getPriority());
        sb.append(" ").append(getOperation()).append(" ");
        makeExpression(sb, secondExpression.toMiniString(),
                !(uniqueCommutative() && secondExpression.uniqueCommutative() && getClass() == secondExpression.getClass())
                        && (secondExpression.getPriority() > getPriority() || secondExpression.getPriority() == getPriority()
                        && (!getCommutative() || secondExpression.specialCommutative())));
        return sb.toString();
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return calculate(firstExpression.evaluate(x, y, z), secondExpression.evaluate(x, y, z));
    }

    protected abstract T calculate(T a, T b);

    @Override
    public boolean uniqueCommutative() {
        return false;
    }
}

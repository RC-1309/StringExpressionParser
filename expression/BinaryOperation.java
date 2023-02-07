package expression;

import java.util.Objects;

public abstract class BinaryOperation implements ExtendedExpression {
    protected final ExtendedExpression firstExpression;
    protected final ExtendedExpression secondExpression;

    public BinaryOperation(ExtendedExpression firstExpression, ExtendedExpression secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryOperation that = (BinaryOperation) o;
        return firstExpression.equals(that.firstExpression) && secondExpression.equals(that.secondExpression);
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
    public double evaluate(double x) {
        return calculate(firstExpression.evaluate(x), secondExpression.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return calculate(firstExpression.evaluate(x, y, z), secondExpression.evaluate(x, y, z));
    }

    protected abstract int calculate(int a, int b);

    protected abstract double calculate(double a, double b);

    @Override
    public boolean uniqueCommutative() {
        return false;
    }

    @Override
    public int evaluate(int x) {
        return calculate(firstExpression.evaluate(x), secondExpression.evaluate(x));
    }
}

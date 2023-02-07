package expression;

public abstract class UnaryOperation implements ExtendedExpression {
    protected final ExtendedExpression expression;

    public UnaryOperation(ExtendedExpression expression) {
        this.expression = expression;
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
    public double evaluate(double x) {
        return calculate(expression.evaluate(x));
    }

    @Override
    public int evaluate(int x) {
        return calculate(expression.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return calculate(expression.evaluate(x, y, z));
    }

    protected abstract int calculate(int a);

    protected abstract double calculate(double a);

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

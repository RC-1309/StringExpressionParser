package expression;

public class Multiply extends BinaryOperation {
    private static final String OPERATION = "*";

    public Multiply(ExtendedExpression firstExpression, ExtendedExpression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    public boolean getCommutative() {
        return true;
    }

    public String getOperation() {
        return OPERATION;
    }

    @Override
    protected int calculate(int a, int b) {
        return a * b;
    }

    @Override
    public int getPriority() {
        return Priority.getMultiplyPriority();
    }

    @Override
    protected double calculate(double a, double b) {
        return a * b;
    }
}

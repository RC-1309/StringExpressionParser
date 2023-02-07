package expression;

public class Subtract extends BinaryOperation {
    private static final String OPERATION = "-";

    public Subtract(ExtendedExpression firstExpression, ExtendedExpression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    public boolean getCommutative() {
        return false;
    }

    public String getOperation() {
        return OPERATION;
    }

    @Override
    protected int calculate(int a, int b) {
        return a - b;
    }

    @Override
    public int getPriority() {
        return Priority.getSubtractPriority();
    }

    @Override
    protected double calculate(double a, double b) {
        return a - b;
    }
}

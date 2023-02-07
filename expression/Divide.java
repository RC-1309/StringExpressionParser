package expression;

public class Divide extends BinaryOperation {
    private static final String OPERATION = "/";

    public Divide(ExtendedExpression firstExpression, ExtendedExpression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    public boolean getCommutative() {
        return false;
    }

    @Override
    public boolean specialCommutative() {
        return true;
    }

    @Override
    protected int calculate(int a, int b) {
        return a / b;
    }

    public String getOperation() {
        return OPERATION;
    }

    @Override
    public int getPriority() {
        return Priority.getDividePriority();
    }

    @Override
    protected double calculate(double a, double b) {
        return a / b;
    }
}

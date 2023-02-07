package expression;

public class Minus extends UnaryOperation {
    private static final String OPERATION = "-";

    public Minus(ExtendedExpression expression) {
        super(expression);
    }

    public String getOperation() {
        return OPERATION;
    }

    @Override
    protected int calculate(int a) {
        return -a;
    }

    @Override
    protected double calculate(double a) {
        return -a;
    }
}

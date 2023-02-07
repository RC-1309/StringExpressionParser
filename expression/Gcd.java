package expression;

public class Gcd extends BinaryOperation {
    private static final String OPERATION = "gcd";

    public Gcd(ExtendedExpression firstExpression, ExtendedExpression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    protected String getOperation() {
        return OPERATION;
    }

    private static int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }

    private static int abs(int a) {
        if (a < 0) {
            a = -a;
        }
        return a;
    }

    @Override
    protected int calculate(int a, int b) {
        return abs(gcd(abs(a), abs(b)));
    }

    @Override
    protected double calculate(double a, double b) {
        return 0;
    }

    @Override
    public int getPriority() {
        return Priority.getGcdPriority();
    }

    @Override
    public boolean getCommutative() {
        return true;
    }

    @Override
    public boolean specialCommutative() {
        return true;
    }

    @Override
    public boolean uniqueCommutative() {
        return true;
    }
}

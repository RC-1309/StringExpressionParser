package expression;

public class Lcm extends BinaryOperation {
    private static final String OPERATION = "lcm";

    public Lcm(ExtendedExpression firstExpression, ExtendedExpression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    protected String getOperation() {
        return OPERATION;
    }

    protected static int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }

    protected static int abs(int a) {
        if (a < 0) {
            a = -a;
        }
        return a;
    }

    @Override
    protected int calculate(int a, int b) {
        int g = gcd(abs(a), abs(b));
        return g == 0 ? 0 : a / g * b;
    }

    @Override
    protected double calculate(double a, double b) {
        return 0;
    }

    @Override
    public int getPriority() {
        return Priority.getLcmPriority();
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

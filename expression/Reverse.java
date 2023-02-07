package expression;

public class Reverse extends UnaryOperation {
    private static final String OPERATION = "reverse";
    public Reverse(ExtendedExpression expression) {
        super(expression);
    }

    @Override
    protected String getOperation() {
        return OPERATION;
    }

    @Override
    protected int calculate(int a) {
        int ans = 0;
        while (a != 0) {
            ans *= 10;
            ans += a % 10;
            a /= 10;
        }
        return ans;
    }

    @Override
    protected double calculate(double a) {
        if (a < 0) {
            a = -a;
        }
        return Double.parseDouble(new StringBuilder(Double.toString(a)).reverse().toString());
    }
}

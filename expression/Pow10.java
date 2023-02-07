package expression;

public class Pow10 extends UnaryOperation {
    private static final String OPERATION = "pow10";
    public Pow10(ExtendedExpression expression) {
        super(expression);
    }

    @Override
    protected String getOperation() {
        return OPERATION;
    }

    @Override
    protected int calculate(int a) {
        int answer = 1;
        while (a > 0) {
            answer *= 10;
            a--;
        }
        return answer;
    }

    @Override
    protected double calculate(double a) {
        return 0;
    }
}

package expression;

public class Log10 extends UnaryOperation {
    private static final String OPERATION = "log10";
    public Log10(ExtendedExpression expression) {
        super(expression);
    }

    @Override
    protected String getOperation() {
        return OPERATION;
    }

    @Override
    protected int calculate(int a) {
        int answer = 0;
        while (a > 0) {
            a /= 10;
            answer++;
        }
        return answer - 1;
    }

    @Override
    protected double calculate(double a) {
        return 0;
    }
}

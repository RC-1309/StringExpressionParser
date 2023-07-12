package expression.generic;

public class Abs<T extends Number> extends UnaryOperation<T>{
    private static final String OPERATION = "abs";

    public Abs(ExtendedExpression<T> expression, Calculator<T> calculator) {
        super(expression, calculator);
    }

    @Override
    protected String getOperation() {
        return OPERATION;
    }

    @Override
    protected T calculate(T a) {
        return calculator.abs(a);
    }
}

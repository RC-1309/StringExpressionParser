package expression.generic;

public class Negate<T extends Number> extends UnaryOperation<T> {
    private static final String OPERATION = "-";

    public Negate(ExtendedExpression<T> expression, Calculator<T> calculator) {
        super(expression, calculator);
    }

    public String getOperation() {
        return OPERATION;
    }

    protected T calculate(T a) {
        return calculator.negate(a);
    }
}

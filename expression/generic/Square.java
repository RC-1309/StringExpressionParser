package expression.generic;

public class Square<T extends Number> extends UnaryOperation<T>{
    private static final String OPERATION = "square";

    public Square(ExtendedExpression<T> expression, Calculator<T> calculator) {
        super(expression, calculator);
    }

    @Override
    protected String getOperation() {
        return OPERATION;
    }

    @Override
    protected T calculate(T a) {
        return calculator.square(a);
    }
}

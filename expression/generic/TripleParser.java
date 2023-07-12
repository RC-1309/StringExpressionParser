package expression.generic;

public interface TripleParser<T extends Number> {
    TripleExpression<T> parse(String expression, Calculator<T> value);
}

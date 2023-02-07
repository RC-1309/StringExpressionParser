package expression;

import expression.exceptions.ParseStringException;
import expression.parser.ExpressionParser;

public class Main {
    public static void main(String[] args) throws ParseStringException {
        ExpressionParser pars = new ExpressionParser();

        System.out.println((pars.parse("x + 1 * (2 + 3)")).toMiniString());
    }
}

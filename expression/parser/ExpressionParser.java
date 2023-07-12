package expression.parser;

import expression.*;
import expression.exceptions.ParseStringException;

public class ExpressionParser implements TripleParser {
    @Override
    public TripleExpression parse(String expression) throws ParseStringException {
        return new Parser(new StringExpression(expression)).parse();
    }

    public static class Parser extends BaseParser {
        private static final int MAX_PRIORITY = Priority.getMaxPriority();

        protected Parser(StringExpression source) {
            super(source);
        }

        private ExtendedExpression create(boolean needMinus) {
            skipWhitespace();
            StringBuilder sb = new StringBuilder();
            if (needMinus) {
                sb.append('-');
            }
            if (Character.isDigit(ch)) {
                while (Character.isDigit(ch)) {
                    sb.append(take());
                }
                return new Const(Integer.parseInt(sb.toString()));
            } else {
                while (Character.isLetter(ch)) {
                    sb.append(take());
                }
                return new Variable(sb.toString());
            }
        }

        private boolean isOpenBracket() {
            skipWhitespace();
            return take('(');
        }

        private void isCloseBracket() {
            skipWhitespace();
            take(')');
        }

        public TripleExpression parse() throws ParseStringException {
            TripleExpression expression = allPriority(MAX_PRIORITY);
            if (eof()) {
                return expression;
            } else {
                throw new IllegalArgumentException("some mistake");
            }
        }

        public static ExtendedExpression getExpression(String operand, ExtendedExpression left,
                                                       ExtendedExpression right) {
            return switch (operand) {
                case "-" -> new Subtract(left, right);
                case "+" -> new Add(left, right);
                case "*" -> new Multiply(left, right);
                case "/" -> new Divide(left, right);
                case "gcd" -> new Gcd(left, right);
                case "lcm" -> new Lcm(left, right);
                default -> null;
            };
        }

        public ExtendedExpression allPriority(int priority) throws ParseStringException {
            if (priority == 0) {
                return priorityZero();
            }
            ExtendedExpression left = allPriority(priority - 1);
            String[] nameOperand = Priority.getOperand(priority);
            boolean hasNextExpression = true;
            while (hasNextExpression) {
                hasNextExpression = false;
                skipWhitespace();
                for (String operand : nameOperand) {
                    if (test(operand)) {
                        left = getExpression(operand, left, allPriority(priority - 1));
                        hasNextExpression = true;
                        break;
                    }
                }
            }
            return left;
        }

        public ExtendedExpression priorityZero() throws ParseStringException {
            skipWhitespace();
            if (test("reverse")) {
                return new Reverse(priorityZero());
            } else if (take('-')) {
                if (between('0', '9')) {
                    return create(true);
                }
                return new Minus(priorityZero());
            } else if (between('0', '9') || between('x', 'z')) {
                return create(false);
            } else if (take('(')) {
                ExtendedExpression res = allPriority(MAX_PRIORITY);
                isCloseBracket();
                return res;
            } else {
                throw new ParseStringException("This operation not supported");
            }
        }
    }
}



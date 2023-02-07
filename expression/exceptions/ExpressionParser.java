package expression.exceptions;

import expression.*;
import expression.parser.BaseParser;
import expression.parser.StringExpression;
import expression.parser.TripleParser;

import java.util.Set;

public class ExpressionParser implements TripleParser {
    @Override
    public TripleExpression parse(final String expression) {
//        System.err.println(expression);
        return new Parser(new StringExpression(expression)).parse();
    }

    public static class Parser extends BaseParser {
        private static final int MAX_PRIORITY = Priority.getMaxPriority();
        private static final Set<String> SYMBOLIC_OPERATIONS = Priority.getSymbolicOperations();

        protected Parser(final StringExpression source) {
            super(source);
        }

        private ExtendedExpression getNumber(final boolean needMinus) {
            final StringBuilder sb = new StringBuilder();
            if (needMinus) {
                sb.append('-');
            }
            while (Character.isDigit(ch)) {
                sb.append(take());
            }
            try {
                return new Const(Integer.parseInt(sb.toString()));
            } catch (final NumberFormatException e) {
                throw new OverflowException(error("Constant overflow: " + sb));
            }
        }

        public TripleExpression parse() {
            final TripleExpression expression = parseStringToExpression();
            if (eof()) {
                return expression;
            } else {
                final StringBuilder sb = new StringBuilder();
                int size = 0;
                while (!eof() && size < 10) {
                    size++;
                    sb.append(take());
                }
                throw new EndOfFileException(error("End of file not expect, found: " + sb));
            }
        }

        public TripleExpression parseStringToExpression() {
            return allPriority(MAX_PRIORITY);
        }

        public ExtendedExpression getBinaryExpression(final String operand, final ExtendedExpression left, final ExtendedExpression right) {
            return switch (operand) {
                case "-" -> new CheckedSubtract(left, right);
                case "+" -> new CheckedAdd(left, right);
                case "*" -> new CheckedMultiply(left, right);
                case "/" -> new CheckedDivide(left, right);
                case "gcd" -> new CheckGcd(left, right);
                case "lcm" -> new CheckLcm(left, right);
                default -> throw new IncorrectOperandException(error("Incorrect operand: " + operand));
            };
        }

        public ExtendedExpression getUnaryExpression(final String operand, final ExtendedExpression expression) {
            return switch (operand) {
                case "log10" -> new CheckLog10(expression);
                case "pow10" -> new CheckPow10(expression);
                case "reverse" -> new CheckReverse(expression);
                default -> throw new IncorrectOperandException(error("Incorrect operand: " + operand));
            };
        }

        private boolean checkSymbolicOperations(String operation) {
            return SYMBOLIC_OPERATIONS.contains(operation);
        }

        private boolean checkNext() {
            return !Character.isWhitespace(ch) && ch != '(' && ch != '-';
        }

        public ExtendedExpression allPriority(final int priority) {
            if (priority == 0) {
                return unaryOperations();
            }
            ExtendedExpression left = allPriority(priority - 1);
            final String[] nameOperand = Priority.getOperand(priority);
            boolean hasNextExpression = true;
            while (hasNextExpression) {
                hasNextExpression = false;
                skipWhitespace();
                for (final String operand : nameOperand) {
                    if (test(operand)) {
                        if (!checkSymbolicOperations(operand) && checkNext()) {
                            throw new WrongFormatException(error("Wrong format: " + operand + foundSymbol()));
                        }
                        final ExtendedExpression right = allPriority(priority - 1);
                        left = getBinaryExpression(operand, left, right);
                        hasNextExpression = true;
                        break;
                    }
                }
            }
            return left;
        }

        private String foundSymbol() {
            return (ch == END ? "EOF" : Character.toString(ch));
        }

        public ExtendedExpression unaryOperations() {
            final String[] nameOperand = Priority.getOperand(0);
            skipWhitespace();
            for (final String operand : nameOperand) {
                if (test(operand)) {
                    if (checkNext()) {
                        throw new WrongFormatException(error("Wrong unary format: " + operand + foundSymbol()));
                    }
                    return getUnaryExpression(operand, unaryOperations());
                }
            }
            return priorityZero();
        }

        public ExtendedExpression priorityZero()  {
            skipWhitespace();
            if (take('-')) {
                if (between('0', '9')) {
                    return getNumber(true);
                }
                final ExtendedExpression expression = unaryOperations();
                return new CheckedNegate(expression);
            } else if (between('0', '9')) {
                return getNumber(false);
            } else if (between('x', 'z')) {
                return new Variable(Character.toString(take()));
            } else if (take('(')) {
                final ExtendedExpression cur = allPriority(MAX_PRIORITY);
                skipWhitespace();
                if (take(')')) {
                    return cur;
                } else {
                    throw new MissingCloseBracketException(error("Cannot find close bracket, found: " + foundSymbol()));
                }
            } else {
                throw new MissingArgumentException(error("Missing argument, found: " + foundSymbol()));
            }
        }
    }
}

package expression.generic;

import expression.exceptions.*;

import java.util.Set;

public class ExpressionParser<T extends Number> implements TripleParser<T> {
    @Override
    public TripleExpression<T> parse(final String expression, final Calculator<T> calculator) {
//        System.err.println(expression);
        return new Parser(new StringExpression(expression), calculator).parse();
    }

    public class Parser extends BaseParser<T> {
        private final Calculator<T> calculator;
        private static final Set<String> SYMBOLIC_OPERATIONS = Set.of("-", "*", "/", "+");
        private static final int MAX_PRIORITY = 2;
        private static final String[] ZERO_PRIORITY = {"abs", "square"};
        private static final String[] ONE_PRIORITY = {"/", "*", "mod"};
        private static final String[] TWO_PRIORITY = {"-", "+"};

        public static String[] getOperand(int priority) throws WrongPriorityException {
            return switch (priority) {
                case 0 -> ZERO_PRIORITY;
                case 1 -> ONE_PRIORITY;
                case 2 -> TWO_PRIORITY;
                default -> throw new WrongPriorityException("Wrong priority");
            };
        }

        protected Parser(final StringExpression source, final Calculator<T> calculator) {
            super(source);
            this.calculator = calculator;
        }

        private ExtendedExpression<T> getNumber(final boolean needMinus) {
            final StringBuilder sb = new StringBuilder();
            if (needMinus) {
                sb.append('-');
            }
            while (Character.isDigit(ch) || ch == '.') {
                sb.append(take());
            }
            try {
                return new Const<>(calculator.create(sb.toString()));
            } catch (final NumberFormatException e) {
                throw new OverflowException(error("Constant overflow: " + sb));
            }
        }

        public TripleExpression<T> parse() {
            final TripleExpression<T> expression = parseStringToExpression();
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

        private TripleExpression<T> parseStringToExpression() {
            return allPriority(MAX_PRIORITY);
        }

        private ExtendedExpression<T> getBinaryExpression(final String operand,
                                                          final ExtendedExpression<T> left,
                                                          final ExtendedExpression<T> right) {
            return switch (operand) {
                case "-" -> new Subtract<>(left, right, calculator);
                case "+" -> new Add<>(left, right, calculator);
                case "*" -> new Multiply<>(left, right, calculator);
                case "/" -> new Divide<>(left, right, calculator);
                case "mod" -> new Module<>(left, right, calculator);
                default -> throw new IncorrectOperandException(error("Incorrect operand: " + operand));
            };
        }

        private ExtendedExpression<T> getUnaryExpression(final String operand, final ExtendedExpression<T> expression) {
            return switch (operand) {
                case "square" -> new Square<>(expression, calculator);
                case "abs" -> new Abs<>(expression, calculator);
                default -> throw new IncorrectOperandException(error("Incorrect operand: " + operand));
            };
        }

        private boolean checkSymbolicOperations(String operation) {
            return SYMBOLIC_OPERATIONS.contains(operation);
        }

        private boolean checkNext() {
            return !Character.isWhitespace(ch) && ch != '(' && ch != '-';
        }

        private ExtendedExpression<T> allPriority(final int priority) {
            if (priority == 0) {
                return unaryOperations();
            }
            ExtendedExpression<T> left = allPriority(priority - 1);
            final String[] nameOperand = getOperand(priority);
            boolean hasNextExpression = true;
            while (hasNextExpression) {
                hasNextExpression = false;
                skipWhitespace();
                for (final String operand : nameOperand) {
                    if (test(operand)) {
                        if (!checkSymbolicOperations(operand) && checkNext()) {
                            throw new WrongFormatException(error("Wrong format: " + operand + foundSymbol()));
                        }
                        final ExtendedExpression<T> right = allPriority(priority - 1);
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

        private ExtendedExpression<T> unaryOperations() {
            final String[] nameOperand = getOperand(0);
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

        private ExtendedExpression<T> priorityZero() {
            skipWhitespace();
            if (take('-')) {
                if (between('0', '9')) {
                    return getNumber(true);
                }
                final ExtendedExpression<T> expression = unaryOperations();
                return new Negate<>(expression, calculator);
            } else if (between('0', '9')) {
                return getNumber(false);
            } else if (between('x', 'z')) {
                return new Variable<>(Character.toString(take()));
            } else if (take('(')) {
                final ExtendedExpression<T> cur = allPriority(MAX_PRIORITY);
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

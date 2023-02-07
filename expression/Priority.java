package expression;

import expression.exceptions.WrongPriorityException;

import java.util.Set;

public class Priority {
    private static final int unaryOperationPriority = 0;
    private static final int constAndVariablePriority = 0;
    private static final int dividePriority = 1;
    private static final int multiplyPriority = 1;
    private static final int addPriority = 2;
    private static final int subtractPriority = 2;
    private static final int gcdPriority = 3;
    private static final int lcmPriority = 3;
    private static final int MAX_PRIORITY = 3;
    private static final String[] ZERO_PRIORITY = {"reverse", "log10", "pow10"};
    private static final String[] ONE_PRIORITY = {"/", "*"};
    private static final String[] TWO_PRIORITY = {"-", "+"};
    private static final String[] THREE_PRIORITY = {"gcd", "lcm"};
    private static final Set<String> SYMBOLIC_OPERATIONS = Set.of("-", "*", "/", "+");

    public static Set<String> getSymbolicOperations() {
        return SYMBOLIC_OPERATIONS;
    }

    public static String[] getOperand(int priority) throws WrongPriorityException {
        return switch (priority) {
            case 0 -> ZERO_PRIORITY;
            case 1 -> ONE_PRIORITY;
            case 2 -> TWO_PRIORITY;
            case 3 -> THREE_PRIORITY;
            default -> throw new WrongPriorityException("Wrong priority");
        };
    }

    public static int getUnaryOperationPriority() {
        return unaryOperationPriority;
    }

    public static int getConstAndVariablePriority() {
        return constAndVariablePriority;
    }

    public static int getDividePriority() {
        return dividePriority;
    }

    public static int getMultiplyPriority() {
        return multiplyPriority;
    }

    public static int getAddPriority() {
        return addPriority;
    }

    public static int getSubtractPriority() {
        return subtractPriority;
    }

    public static int getGcdPriority() {
        return gcdPriority;
    }

    public static int getLcmPriority() {
        return lcmPriority;
    }

    public static int getMaxPriority() {
        return MAX_PRIORITY;
    }
}

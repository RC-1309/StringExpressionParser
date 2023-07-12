package expression.generic;

public interface ExtendedExpression<T extends Number> extends TripleExpression<T> {
    int getPriority();
    // 0 for UnaryOperation, Const and Variable
    // 1 for Multiply and Divide
    // 2 for Subtract and Add
    // 3 for Gcd, Lcm

    boolean specialCommutative();
    // true for Divide
    // false for another

    boolean getCommutative();
    // true: Const, Variable, Gcd, Lcm Add and Multiply
    // false: Unary, Divide and Subtract

    boolean uniqueCommutative();
    // true: Gcd, Lcm
    // false: another

    String toMiniString();
}

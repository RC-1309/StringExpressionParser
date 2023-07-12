package expression.generic;

import expression.Priority;

public class Variable<T extends Number> implements ExtendedExpression<T> {
    private final String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    @Override
    public boolean getCommutative() {
        return true;
    }

    @Override
    public boolean specialCommutative() {
        return false;
    }

    @Override
    public boolean uniqueCommutative() {
        return false;
    }

    @Override
    public int getPriority() {
        return Priority.getConstAndVariablePriority();
    }

    @Override
    public String toString() {
        return variable;
    }

    @Override
    public int hashCode() {
        return variable.hashCode();
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public T evaluate(T x, T y, T z) {
        if ("x".equals(variable)) {
            return x;
        } else if ("y".equals(variable)) {
            return y;
        } else if ("z".equals(variable)) {
            return z;
        }
        return null;
    }
}

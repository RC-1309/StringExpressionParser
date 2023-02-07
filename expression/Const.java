package expression;

import java.util.Objects;

public class Const implements ExtendedExpression {
    private final double value;
    private final boolean isDouble;

    public Const(int value) {
        this.value = value;
        isDouble = false;
    }

    public Const(double value) {
        this.value = value;
        isDouble = true;
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
    public boolean getCommutative() {
        return true;
    }

    @Override
    public int getPriority() {
        return Priority.getConstAndVariablePriority();
    }

    @Override
    public int evaluate(int x) {
        return (int) value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, getClass());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Const that = (Const) o;
        return value == that.value;
    }

    @Override
    public String toString() {
        return isDouble ? Double.toString(value) : Integer.toString((int) value);
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public double evaluate(double x) {
        return value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return (int) value;
    }
}

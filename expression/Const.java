package expression;

import java.util.Objects;

public class Const implements ExtendedExpression {
    private final Number value;

    public Const(int value) {
        this.value = value;
    }

    public Const(double value) {
        this.value = value;
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
        return Objects.equals(value, that.value);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public double evaluate(double x) {
        return value.doubleValue();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value.intValue();
    }
}

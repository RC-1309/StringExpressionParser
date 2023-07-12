package expression.generic;

import expression.Priority;

import java.util.Objects;

public class Const<T extends Number> implements ExtendedExpression<T> {
    private final T myNumber;

    public Const(T myNumber) {
        this.myNumber = myNumber;
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
    public int hashCode() {
        return Objects.hash(myNumber, getClass());
    }

    @Override
    public String toString() {
        return myNumber.toString();
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return myNumber;
    }
}

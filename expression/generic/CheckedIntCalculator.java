package expression.generic;

import expression.exceptions.CheckExceptions;

public class CheckedIntCalculator implements Calculator<Integer> {
    @Override
    public Integer add(Integer a, Integer b) {
        return CheckExceptions.add(a, b);
    }

    @Override
    public Integer sub(Integer a, Integer b) {
        return CheckExceptions.sub(a, b);
    }

    @Override
    public Integer mul(Integer a, Integer b) {
        return CheckExceptions.mul(a, b);
    }

    @Override
    public Integer div(Integer a, Integer b) {
        return CheckExceptions.div(a, b);
    }

    @Override
    public Integer mod(Integer a, Integer b) {
        return CheckExceptions.mod(a, b);
    }

    @Override
    public Integer negate(Integer a) {
        return CheckExceptions.neg(a);
    }

    @Override
    public Integer abs(Integer a) {
        return CheckExceptions.abs(a);
    }

    @Override
    public Integer square(Integer a) {
        return CheckExceptions.square(a);
    }

    @Override
    public Integer create(String a) {
        return Integer.parseInt(a);
    }
}

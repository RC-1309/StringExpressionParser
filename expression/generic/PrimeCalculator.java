package expression.generic;

import expression.exceptions.DivisionByZero;

public class PrimeCalculator implements Calculator<Integer> {
    private static final int primeMod = 10079;

    private static int createCorrectNumber(int a) {
        return (a % primeMod + primeMod) % primeMod;
    }

    @Override
    public Integer add(Integer a, Integer b) {
        return createCorrectNumber(a + b);
    }

    @Override
    public Integer sub(Integer a, Integer b) {
        return createCorrectNumber(a - b);
    }

    @Override
    public Integer mul(Integer a, Integer b) {
        return createCorrectNumber(a * b);
    }

    private static int binPow(int a, int n, int module) {
        int ans = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                ans = (a * ans) % module;
            }
            a = (a * a) % module;
            n >>= 1;
        }
        return ans;
    }

    @Override
    public Integer div(Integer a, Integer b) {
        if (b == 0) {
            throw new DivisionByZero("division by zero");
        }
        return createCorrectNumber(binPow(b, primeMod - 2, primeMod) * a);
    }

    @Override
    public Integer mod(Integer a, Integer b) {
        return createCorrectNumber(a % b);
    }

    @Override
    public Integer negate(Integer a) {
        return createCorrectNumber(-a);
    }

    @Override
    public Integer abs(Integer a) {
        return createCorrectNumber(Math.abs(a));
    }

    @Override
    public Integer square(Integer a) {
        return mul(a, a);
    }

    @Override
    public Integer create(String a) {
        return createCorrectNumber(Integer.parseInt(a));
    }
}

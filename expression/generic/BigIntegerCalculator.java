package expression.generic;

import java.math.BigInteger;

public class BigIntegerCalculator implements Calculator<BigInteger> {
    @Override
    public BigInteger add(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    @Override
    public BigInteger sub(BigInteger a, BigInteger b) {
        return a.subtract(b);
    }

    @Override
    public BigInteger mul(BigInteger a, BigInteger b) {
        return a.multiply(b);
    }

    @Override
    public BigInteger div(BigInteger a, BigInteger b) {
        return a.divide(b);
    }

    @Override
    public BigInteger mod(BigInteger a, BigInteger b) {
        return a.mod(b);
    }

    @Override
    public BigInteger negate(BigInteger a) {
        return a.negate();
    }

    @Override
    public BigInteger abs(BigInteger a) {
        return a.abs();
    }

    @Override
    public BigInteger square(BigInteger a) {
        return mul(a, a);
    }

    @Override
    public BigInteger create(String a) {
        return new BigInteger(a);
    }
}

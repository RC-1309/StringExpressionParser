package expression.generic;

public class ShortIntCalculator implements Calculator<Short> {
    @Override
    public Short add(Short a, Short b) {
        return (short) (a + b);
    }

    @Override
    public Short sub(Short a, Short b) {
        return (short) (a - b);
    }

    @Override
    public Short mul(Short a, Short b) {
        return (short) (a * b);
    }

    @Override
    public Short div(Short a, Short b) {
        return (short) (a / b);
    }

    @Override
    public Short mod(Short a, Short b) {
        return (short) (a % b);
    }

    @Override
    public Short negate(Short a) {
        return (short)-a;
    }

    @Override
    public Short abs(Short a) {
        return (short)Math.abs(a);
    }

    @Override
    public Short square(Short a) {
        return mul(a, a);
    }

    @Override
    public Short create(String a) {
        return (short) Integer.parseInt(a);
    }
}

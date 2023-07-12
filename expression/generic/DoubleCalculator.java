package expression.generic;

public class DoubleCalculator implements Calculator<Double> {
    @Override
    public Double add(Double a, Double b) {
        return a + b;
    }

    @Override
    public Double sub(Double a, Double b) {
        return a - b;
    }

    @Override
    public Double mul(Double a, Double b) {
        return a * b;
    }

    @Override
    public Double div(Double a, Double b) {
        return a / b;
    }

    @Override
    public Double mod(Double a, Double b) {
        return a % b;
    }

    @Override
    public Double negate(Double a) {
        return -a;
    }

    @Override
    public Double abs(Double a) {
        return Math.abs(a);
    }

    @Override
    public Double square(Double a) {
        return a * a;
    }

    @Override
    public Double create(String a) {
        return Double.parseDouble(a);
    }
}

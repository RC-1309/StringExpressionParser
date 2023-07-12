package expression.generic;

public class UncheckedIntCalculator implements Calculator<Integer> {
    @Override
    public Integer add(Integer a, Integer b) {
        return a + b;
    }

    @Override
    public Integer sub(Integer a, Integer b) {
        return a - b;
    }

    @Override
    public Integer mul(Integer a, Integer b) {
        return a * b;
    }

    @Override
    public Integer div(Integer a, Integer b) {
        return a / b;
    }

    @Override
    public Integer mod(Integer a, Integer b) {
        return a % b;
    }

    @Override
    public Integer negate(Integer a) {
        return -a;
    }

    @Override
    public Integer abs(Integer a) {
        return Math.abs(a);
    }

    @Override
    public Integer square(Integer a) {
        return a * a;
    }

    @Override
    public Integer create(String a) {
        return Integer.parseInt(a);
    }
}

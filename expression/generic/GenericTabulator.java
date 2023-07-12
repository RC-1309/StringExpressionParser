package expression.generic;

public class GenericTabulator implements Tabulator {
    public static Calculator<? extends Number> getMode(String mode) throws UnsupportedModeException {
        return switch (mode) {
            case "i" -> new CheckedIntCalculator();
            case "d" -> new DoubleCalculator();
            case "bi" -> new BigIntegerCalculator();
            case "u" -> new UncheckedIntCalculator();
            case "p" -> new PrimeCalculator();
            case "s" -> new ShortIntCalculator();
            default -> throw new UnsupportedModeException("Mode: " + mode + " is unsupported");
        };
    }
    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        return get(expression, getMode(mode), x1, x2, y1, y2, z1, z2);
    }

    static <T extends Number> Object[][][] get(String expression, Calculator<T> calculator, int x1, int x2, int y1, int y2, int z1, int z2) {
        Object[][][] result = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                for (int z = z1; z <= z2; z++) {
                    try {
                        result[x - x1][y - y1][z - z1] = new ExpressionParser<T>()
                                .parse(expression, calculator)
                                .evaluate(calculator.create(Integer.toString(x)),
                                        calculator.create(Integer.toString(y)),
                                        calculator.create(Integer.toString(z)));
                    } catch (Exception e) {
                        result[x - x1][y - y1][z - z1] = null;
                    }
                }
            }
        }
        return result;
    }
}

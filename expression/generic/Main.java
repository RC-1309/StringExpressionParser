package expression.generic;

public class Main {
    private static final int minValue = -2;
    private static final int maxValue = 2;

    public static void main(String[] args) throws UnsupportedModeException {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            sb.append(args[i]);
        }
        Object[][][] result = GenericTabulator.get(sb.toString(), GenericTabulator.getMode(args[0]), minValue, maxValue, minValue, maxValue, minValue, maxValue);
        for (Object[][] objects : result) {
            for (Object[] object : objects) {
                for (Object o : object) {
                    System.out.print(o + " ");
                }
                System.out.println();
            }
            System.out.println("------------------------");
        }
    }
}

package expression.generic;

public interface Calculator<T extends Number> {
    T add(T a, T b);

    T sub(T a, T b);

    T mul(T a, T b);

    T div(T a, T b);

    T mod(T a, T b);

    T negate(T a);

    T abs(T a);

    T square(T a);

    T create(String a);
}

package computerrepairservice.lambdas;

public interface ToIntFunction<T> {
    int applyAsInt(T value);
}
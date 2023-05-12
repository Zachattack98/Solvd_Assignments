package computerrepairservice.lambdas;

public interface Function<T,V> {
    float apply(T t);
}
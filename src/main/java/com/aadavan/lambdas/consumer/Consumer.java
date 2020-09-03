package com.aadavan.lambdas.consumer;

@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);

    default Consumer<T> andThen(Consumer<T> other) {
        System.out.println("Constructing the lambda.");
        //Objects.requireNonNull(other);
        return (T t) -> {
            this.accept(t);
            other.accept(t);
        };
    }
}

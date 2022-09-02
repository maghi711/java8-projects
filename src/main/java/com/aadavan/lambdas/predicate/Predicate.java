package com.aadavan.lambdas.predicate;

import java.util.Objects;

@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);

    /**
     * Combine two predicates by chaining them.
     * @param other the other predicate
     * @return the functional implementation which chains two predicate.
     */
    default Predicate<T> andThen(Predicate<T> other) {
        Objects.requireNonNull(other);
        return (t) -> this.test(t) && other.test(t);
    }

    /**
     * Returns the inverse/opposite of the test being performed
     * @return the inverse/opposite predicate.
     */
    default Predicate<T> negate() {
        return (T t) -> !this.test(t);
    }
}

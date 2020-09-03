package com.aadavan.lambdas.predicate;

/**
 * Program to test the predicate chaining.
 */
public class TestPredicate {
    public static void main(String[] args) {
        Predicate<String> isNotNull = s -> s != null;
        Predicate<String> isNotEmpty = s -> !s.isEmpty();
        Predicate<String> isNotNullAndNotEmpty = s -> {
            return isNotNull.test(s) && isNotEmpty.test(s);
        };
        Predicate<String> chainedPredicate = isNotNull.andThen(isNotEmpty.negate());
        System.out.println("isNotNullAndNotEmpty = " + isNotNullAndNotEmpty.test("Aadavan"));
        System.out.println("isNotNullAndNotEmpty = " + isNotNullAndNotEmpty.test(null));
        System.out.println("isNotNullAndNotEmpty = " + isNotNullAndNotEmpty.test(""));

        System.out.println("chainedPredicate.test(\"Aadavan\") = " + chainedPredicate.test("Aadavan"));
        System.out.println("chainedPredicate.test(null) = " + chainedPredicate.test(null));
        System.out.println("chainedPredicate.test(\"\") = " + chainedPredicate.test(""));

        //System.out.println("chainedPredicate.negate() = " + chainedPredicate.negate());
        //System.out.println("chainedPredicate.test(null) = " + chainedPredicate.test(null));
        //System.out.println("chainedPredicate.test(\"\") = " + chainedPredicate.test(""));
    }
}

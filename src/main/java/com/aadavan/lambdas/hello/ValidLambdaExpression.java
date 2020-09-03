package com.aadavan.lambdas.hello;

public class ValidLambdaExpression {

    public static void main(String[] args) {
        FIIntCheck count = () -> {
            return 42;
        };
        System.out.println("message(count) = " + message(count));

        FIStringCheck message = (m) -> m + "The message count is ";
        System.out.println("message.message(\"Aadavan\") = " + message.message("Aadavan"));

        GenericFiCheck<String> genericFiCheck = () -> {
            return "Aadavan is mass";
        };
        System.out.println("genericFiCheck = " + genericFiCheck.check());
    }

    static String message(FIIntCheck count) {
        return "The count is " + count.count();
    }
}

@FunctionalInterface
interface FIIntCheck {
    public int count();
}

@FunctionalInterface
interface FIStringCheck {
    String message(String message);
}

@FunctionalInterface
interface GenericFiCheck<T> {
    T check();
}

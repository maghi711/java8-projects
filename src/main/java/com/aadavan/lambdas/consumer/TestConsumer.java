package com.aadavan.lambdas.consumer;

/**
 * Tests the java.util.function.Consumer<T> by writing a custom functional interface.
 */
public class TestConsumer {
    public static void main(String[] args) {
        Consumer<String> printToConsole = s -> {
            System.out.println("Printing to Console = " + s);
        };

        Consumer<String> printToFile = s -> {
            System.out.println("Printing to File = " + s);
        };

        // Manually specifying the logic, if this needs to be changed then
        // logic in the method has to be changed.
        Consumer<String> withoutChainingPrintBoth = s -> {
            printToConsole.accept(s);
            printToFile.accept(s);
        };

        withoutChainingPrintBoth.accept("Aadavan");

        Consumer<String> usingChainingPrintBoth = printToConsole.andThen(printToFile);
        printToFile.andThen(printToConsole).accept("Aadavan Mahesh");
        usingChainingPrintBoth.accept("My All new Aadavan 6");
        System.out.println("Completed.");
    }
}

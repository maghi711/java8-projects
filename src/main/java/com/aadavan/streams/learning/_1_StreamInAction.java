package com.aadavan.streams.learning;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class _1_StreamInAction {

    public static void main(String[] args) {
        createStreamFromAList();
        createStreamFromStrings();
        createStreamFromAnArray();
    }

    private static void createStreamFromAnArray() {
        String[] words = {"hello", "hola", "hallo", "ciao"};
        Stream<String> stream = Stream.of(words);
        stream.forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    private static void createStreamFromStrings() {
        final Stream<String> stream = Stream.of("hello", "hola", "hallo", "ciao");
        stream.forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    private static void createStreamFromAList() {
        List<String> words = Arrays.asList("Aadavan", null, "Mahesh");
        final Stream<String> stream = words.stream();
        stream.forEach(x -> {
            try {
                System.out.print(x.toString());
            } catch (Exception e) {
                System.out.println("\nException occurred " + e.getMessage());
            }
        }
        );
        System.out.println();
    }
}

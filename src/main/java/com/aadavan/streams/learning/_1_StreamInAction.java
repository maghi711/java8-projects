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
        System.out.println("stream = " + stream);
    }

    private static void createStreamFromStrings() {
        final Stream<String> stream = Stream.of("hello", "hola", "hallo", "ciao");
        System.out.println("stream = " + stream);
    }

    private static void createStreamFromAList() {
        List<String> words = Arrays.asList("Aadavan", "Swetha", "Mahesh");
        final Stream<String> stream = words.stream();
        System.out.println("stream = " + stream);
    }
}

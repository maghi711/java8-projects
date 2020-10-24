package com.aadavan.streams.learning;

import java.util.stream.Stream;

public class NumberIterator {
    public static void main(String[] args) {
        Stream.iterate(0, n -> n + 1)
                .limit(100)
                .filter(x -> x%2==0)
                .forEach(x -> {
                    System.out.print(x);
                    System.out.println();
                });
    }
}

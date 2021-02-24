package com.aadavan.streams.learning;

import java.util.stream.Stream;

public class NumberIterator {
    public static void main(String[] args) {
        fourthStream();
    }

    static void firstStream() {
        Stream.iterate(0, n -> n + 1)
            .limit(100)
            .filter(x -> x%2==0)
            .forEach(x -> {
                System.out.print(x);
                System.out.println();
            });
    }
    static void secondStreamForIteration() {
        Stream.iterate(0, n -> n + 1)
            .limit(10)
            .filter(x -> x % 2 == 0)
            .forEach(
                s -> {
                    System.out.println(s);
                }
            );
    }
    static void thirdStream() {
        Stream.iterate(0, n -> n + 2)
                .limit(50)
                .filter(n -> n %2 == 0)
                .forEach(
                        a -> {
                            System.out.println(a);
                        }
                );
    }
    static void fourthStream() {
        Stream.iterate(0, n -> n + 1)
                .limit(20)
                .filter(x -> x %2 == 0)
                .forEach(s -> System.out.println(s));
    }
}

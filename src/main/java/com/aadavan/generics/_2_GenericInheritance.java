package com.aadavan.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class _2_GenericInheritance {

    public static void main(String[] args) {
        List<Number> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2.0);
        System.out.println("numbers = " + numbers);

        List<Integer> ints = new ArrayList<>();
        List<? extends Number> nInts = ints;
        //addNumbers(nInts);
        addNumbers(numbers);
    }

    private static void addNumbers(List<? super Number> numbers) {
        numbers.add(1);
        numbers.add(2.0);
    }

    private static void addNumbers2(Collection<? extends Number> numbers) {
        //numbers.add(1);
        //numbers.add(2.0);
    }

}

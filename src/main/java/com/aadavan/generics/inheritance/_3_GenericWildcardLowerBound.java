package com.aadavan.generics.inheritance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _3_GenericWildcardLowerBound {
    public static void main(String[] args) {
        List<Integer> age = Arrays.asList(6, 36, 35);
        List<Double> exactAge = Arrays.asList(6.6, 36.2, 35.1);
        List<? super Serializable> numbers = new ArrayList<>();
        addToList(numbers, new Integer(20));
        addToList(numbers, new Double(20));
        printList(numbers);
    }

    private static void addToList(List<? super Number> numbers, Number number) {
        numbers.add(number);
    }

    /**
     * The ? here says there is a type associated with the List, which is unknown during compile time.
     * ? -> allows only to read values/elements from the list.
     * ? -> says you cannot add anything to the list
     * @param list
     */
    private static void printList(List<?> list) {
        list.forEach(System.out::println);
    }
}

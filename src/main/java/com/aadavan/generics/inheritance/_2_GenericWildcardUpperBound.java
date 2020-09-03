package com.aadavan.generics.inheritance;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class _2_GenericWildcardUpperBound {
    public static void main(String[] args) {
        List<Integer> age = Arrays.asList(6, 36, 35);
        List<Double> exactAge = Arrays.asList(6.6, 36.2, 35.1);
        printList(exactAge);
        printList(age);
    }

    /**
     * The ? here says there is a type associated with the List, which is unknown during compile time.
     * ? -> allows only to read values/elements from the list.
     * ? -> says you cannot add anything to the list
     * @param list
     */
    private static void printList(List<? extends Number> list) {
        list.forEach(System.out::println);
        final Number number = list.get(0);
        System.out.println("number = " + number);

        /*list.add(new Integer(2));
        list.add(new Double(24.0));*/
    }
}

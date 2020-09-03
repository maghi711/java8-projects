package com.aadavan.generics.inheritance;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class _1_GenericWildcard {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Aadavan", "Swetha", "Mahesh");
        List<Integer> age = Arrays.asList(6, 36, 35);
        printList(names);
        printList(age);
        printList(Arrays.asList(new Date(), new Date(), new Date()));
    }

    /**
     * The ? here says there is a type associated with the List, which is unknown during compile time.
     * ? -> allows only to read values/elements from the list.
     * ? -> says you cannot add anything to the list
     * @param list
     */
    private static void printList(List<?> list) {
        list.forEach(System.out::println);
        final Object value = list.get(0);
        System.out.println("value = " + value);
    }

    private static void printAllList(List list) {
        list.forEach(System.out::println);
    }
}

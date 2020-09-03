package com.aadavan.lambdas.learning;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class _1_FirstLambda {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(2, 5, 1, 10, 7, 6);

        Comparator<Integer> ascendingSort = (first, second) -> first.compareTo(second);
        sort(numbers, ascendingSort);

        Comparator<Integer> descendingSort = (first, second) -> second.compareTo(first);
        sort(numbers, descendingSort);
    }

    static void sort(List<Integer> list, Comparator<Integer> sortingLogic) {
        System.out.println("BEFORE list = " + list);
        Collections.sort(list, sortingLogic);
        System.out.println("AFTER list = " + list);
    }

}

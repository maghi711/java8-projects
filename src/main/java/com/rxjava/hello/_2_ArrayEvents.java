package com.rxjava.hello;

import java.util.ArrayList;
import java.util.List;

public class _2_ArrayEvents {
    public static void main(String[] args) {
        int[] clickEvents = {6, 5, 8, 11, 16, 21};
        NumberPredicate odd = (x) -> {
            if (x%2 == 0) {
                return true;
            }
            return false;
        };
        NumberPredicate even = (x) -> {
            if (x%2 != 0) {
                return true;
            }
            return false;
        };

        System.out.println("even = " + filterIntegers(clickEvents, even));

        System.out.println("odd = " + filterIntegers(clickEvents, odd));
    }

    static List<Integer> filterIntegers(int[] events, NumberPredicate operation) {
        List<Integer> result = new ArrayList<>();
        for (int i : events) {
            if (operation.test(i)) {
                result.add(i);
            }
        }
        return result;
    }
}

interface NumberPredicate {
    boolean test(Integer x);
}

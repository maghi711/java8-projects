package com.aadavan.collections.arrays;

import java.util.Arrays;
import java.util.Objects;

import static java.lang.System.*;

public class ArraysOperation {
    public static void main(String[] args) {
        //iterateArrays();
        //sortArrays();
        searchArrays();
    }

    private static void searchArrays() {
        int[] fib = new int[] {0, 1, 5, 2, 3, 1, 8, 13};
        out.println("names = " + Arrays.toString(fib));
        out.println(Arrays.binarySearch(fib, 3));
        Arrays.sort(fib);
        out.println("names = " + Arrays.toString(fib));
        out.println(Arrays.binarySearch(fib, 3));
    }

    private static void sortArrays() {
        String[] names = new String[]{"aadavan", "swetha", "mahesh"};
        out.println("names = " + Arrays.toString(names));
        Arrays.sort(names);
        out.println("names = " + Arrays.toString(names));
    }

    private static void iterateArrays() {
        String[] names = new String[]{"aadavan", null, "mahesh", "swetha"};
        out.println("names = " + names);
        for (int i = 0; i < names.length; i++) {
            final String name = names[i];
            if (Objects.nonNull(name)) {
                out.println(name.length());
            }
        }
    }
}

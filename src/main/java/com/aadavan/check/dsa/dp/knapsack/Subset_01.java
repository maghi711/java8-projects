package com.aadavan.check.dsa.dp.knapsack;

import java.util.stream.Stream;

public class Subset_01 {

    static int[] a = {5, 2, 9, 6};
    public static void main(String[] args) {
        Stream.iterate(1, n -> n +1)
                .limit(10)
                .forEach(x -> {
                    boolean result = hasSum(x, a.length);
                    System.out.println("for " + x + " result = " + result);
                    if (result) {
                        System.out.println(true);
                    }
                });
    }

    static boolean hasSum(int sum, int size) {
        if (sum == 0) {
            return true;
        }
        else if (sum < 0 || size <= 0) {
            return false;
        }
        return hasSum((sum - a[size-1]), size-1) || hasSum(sum, size - 1);
    }
}

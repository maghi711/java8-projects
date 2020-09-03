package com.aadavan.trident.qa;

import java.util.Arrays;

public class CardNumberGenerator {

    public static void main(String[] args) {
        long initValue = 3372036854775807L;
        final long[] longs = generateNumbers(new long[10000000], initValue);
        System.out.println("longs = " + Arrays.toString(longs));
    }

    static long[] generateNumbers(long[] array, long startvalue) {
        for (int i = 0; i < array.length; i++) {
            array[i] = startvalue + i;
        }
        return array;
    }

}

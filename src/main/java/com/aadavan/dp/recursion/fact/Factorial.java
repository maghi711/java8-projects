package com.aadavan.dp.recursion.fact;

public class Factorial {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println( i + " factorial is " + fact(i));
        }
    }

    static long fact(long n) {
        if (n == 1 || n == 0)
            return 1;
        else
            return n * fact(n - 1);
    }
}

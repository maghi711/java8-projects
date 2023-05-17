package com.aadavan.dp.recursion.fact;

public class Practice {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " = " + f(i));
        }
    }

    static long f(long n) {
        // base case
        if (n == 0 || n == 1)
            return 1;
        // recursive case
        else
            return n * f(n - 1);
    }
}

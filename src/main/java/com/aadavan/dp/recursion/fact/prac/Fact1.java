package com.aadavan.dp.recursion.fact.prac;

public class Fact1 {
    public static void main(String[] args) {
        final long fact = fact(10);
        System.out.println("fact = " + fact);
    }

    static long fact(long input) {
        if (input == 1 || input == 0)
            return 1;
        else
            return input * fact(input -1);
    }
}

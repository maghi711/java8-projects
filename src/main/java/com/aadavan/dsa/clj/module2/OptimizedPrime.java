package com.aadavan.dsa.clj.module2;

public class OptimizedPrime {

    public static void main(String[] args) {
        int n = 17;

        boolean flag = isPrime(n);
        System.out.println("flag = " + flag);
    }

    static boolean isPrime(int n) {
        int count = 0;
        final int end = n / 2;
        System.out.println("end = " + end);
        for (int i = 2; i <= end; i++) {
            System.out.println("i = " + i);
            if (n % i == 0) {
                count++;
                break;
            }
        }
        if (count > 0) {
            return false;
        }
        return true;
    }
}

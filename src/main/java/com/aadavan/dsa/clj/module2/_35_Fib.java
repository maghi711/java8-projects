package com.aadavan.dsa.clj.module2;

public class _35_Fib {
    public static void main(String[] args) {
        int n = 10;
        int a = 0;
        int b = 1;
        if (n > 0) {
            System.out.println(a);
        }
        if (n > 1) {
            System.out.println(b);
        }
        int next = 0;
        for (int i = 2; i < n; i++) {
            next = a + b;
            System.out.println(next);
            a = b;
            b = next;
        }
    }
}

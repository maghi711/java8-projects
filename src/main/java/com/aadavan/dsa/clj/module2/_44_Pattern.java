package com.aadavan.dsa.clj.module2;

public class _44_Pattern {
    public static void main(String[] args) {
        int n=7;
        int mid = n/2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j<=i && j < n/2) {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }
}

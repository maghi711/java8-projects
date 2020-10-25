package com.aadavan.dsa.dp.contest2;

import java.util.Arrays;
import java.util.Scanner;

public class _02_VInkleEuros {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        while (t-- != 0) {
            int size = s.nextInt();
            int a[] = new int[size];
            for (int i = 0; i < size; i++) {
                a[i] = s.nextInt();
            }
        }
    }
    static int maxSum(int a[]) {
        int largest = 0;
        int largestSoFar = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            largest  = largest + a[i];
            if (largestSoFar < largest) {
                largestSoFar = largest;
            }
            if (largest < 0) {
                largest = 0;
            }
        }
        return largestSoFar;
    }
}

package com.aadavan.dsa.dp.contest2;

import java.util.Arrays;
import java.util.Scanner;

public class _01_RangeSum {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        while (t-- != 0) {
            int size = s.nextInt();
            int a[] = new int[size];
            for (int i = 0; i < size; i++) {
                a[i] = s.nextInt();
            }
            int t2 = s.nextInt();
            while(t2-- != 0) {
                int start = s.nextInt();
                int end = s.nextInt();
                int sum = 0;
                for (int i = start; i <= end; i++) {
                    sum += a[i];
                }
                System.out.println(sum);
            }
            System.out.println("Arrays.toString(a) = " + Arrays.toString(a));
        }
        s.close();
    }
}

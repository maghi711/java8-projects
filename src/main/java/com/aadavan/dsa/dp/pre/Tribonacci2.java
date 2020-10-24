package com.aadavan.dsa.dp.pre;

import java.util.Scanner;

/**
 * This program succeeded.
 */
public class Tribonacci2 {
    static int MOD = (int)1e9+7;
    static Scanner scanner = new Scanner(System.in);
    static long sumMOD(long a, long b) {
        return (a%MOD + b%MOD)%MOD;
    }
    public static void main(String[] args)
    {
        int t = scanner.nextInt();
        long[] dp = new long[1001];
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;
        while (t-- != 0) {
            int n = scanner.nextInt();
            System.out.println(tribonacci(n, dp));
        }
    }
    public static Long tribonacci(int n, long[] dp) {
        if (n == 0 || n == 1) {
            return 0l;
        }
        if(n == 2) {
            return 1l;
        }
        if (dp[n] != 0) {
            return dp[n];
        }
        dp[n] = sumMOD(sumMOD(tribonacci(n-1, dp), tribonacci(n-2, dp)), tribonacci(n-3, dp));
        return dp[n];
    }
}

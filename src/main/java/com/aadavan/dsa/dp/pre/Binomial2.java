package com.aadavan.dsa.dp.pre;

import java.util.Scanner;

public class Binomial2 {
    static int MOD = (int)1e9+7;
    static long sumMOD(long a, long b) {
        return (a%MOD + b%MOD)%MOD;
    }
    static Scanner sc = new Scanner(System.in);
    static Long[][] dp = new Long[1001][1001];
    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- !=0 ) {
            int n = sc.nextInt();
            int r = sc.nextInt();
            binomial(n, r);
        }
    }

    private static long binomial(int n, int r) {
        if (r == 0 || r == n) {
            return 1;
        }
        if (dp[n][r] != null) {
            return dp[n][r];
        }
        dp[n][r] = sumMOD(binomial(n-1,r), binomial(n-1, r-1));
        return dp[n][r];
    }
}

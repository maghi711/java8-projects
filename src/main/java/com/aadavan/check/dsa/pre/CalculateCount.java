package com.aadavan.check.dsa.pre;

import java.util.Scanner;

public class CalculateCount {
    static int MOD = (int)1e9+7;
    static long sumMod(long a, long b) {
        return (a%MOD + b%MOD)%MOD;
    }
    static Scanner sc = new Scanner(System.in);
    static long dp[][]= new long[5001][5001];
    public static void main(String[] args) {
/*        int t = sc.nextInt();
        while(t-- != 0) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            System.out.println(count(n,k));
        }*/
        System.out.println(count(4, 3));
    }

    private static Long count(int n, int k) {
        if (n == 0)
            return 0l;
        if (k == 0)
            return 1l;
        if (dp[n][k] != 0)
            return dp[n][k];
        for(int i = 0; i <= Math.min(k, n-1);i++) {
            dp[n][k] = sumMod(dp[n][k], count(n-1, k-i));
        }
        return dp[n][k];
    }
}

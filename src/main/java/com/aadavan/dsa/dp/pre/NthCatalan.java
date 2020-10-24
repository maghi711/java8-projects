package com.aadavan.dsa.dp.pre;

import java.util.Scanner;

public class NthCatalan {
    static int MOD = (int)1e9+7;
    static long sumMOD(long a, long b) {
        return (a%MOD + b%MOD)%MOD;
    }
    static long prodMOD(long a, long b) {
        return (a%MOD * b%MOD)%MOD;
    }
    static Scanner sc = new Scanner(System.in);
    static long dp[] = new long[1001];
    public static void main(String[] args) {
/*        int t = sc.nextInt();
        while(t-- != 0) {
            int n = sc.nextInt();
            System.out.println(nthCatalan(n));
        }*/
        dp[0] = 0;
        dp[1] = 1;
        System.out.println(nthCatalan(8));
    }

    private static long nthCatalan(int n) {
        if (n == 0 || n == 1)
            return 1l;
        if (dp[n] != 0) {
            return dp[n];
        }
        for(int i = 0; i <= n-1; i++) {
            dp[n] = sumMOD(dp[n],prodMOD(nthCatalan(i), nthCatalan(n-i-1)));
        }
        return dp[n];
    }
}

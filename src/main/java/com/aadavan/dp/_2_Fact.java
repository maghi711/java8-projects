package com.aadavan.dp;

import java.util.Scanner;

public class _2_Fact {

    private static final int M= 1000000007;
    public static void main (String[] args) throws java.lang.Exception
    {
        /*System.out.println("Enter input");
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- != 0) {
            int val = sc.nextInt();
            System.out.println(fact(val));
        }*/
        factorial(50);
    }
    public static int fact(int val) {
        if (val == 1) return 1;
        int result = val * fact(val-1);
        return result;
    }

    public static long factorial(int n) {
        long dp[] = new long[n + 10];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = ((i % M) * (dp[i-1] % M)) % M;
        }
        System.out.println("dp[n] = " + dp[n]);
        return 0;
    }
}

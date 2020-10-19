package com.aadavan.check.dsa.pre.contest;

public class FactorialNumber {
    static int MOD = (int)1e9+7;
    static long prodMOD(long a, long b) {
        return (a%MOD * b%MOD)%MOD;
    }
    static long dp[] = new long[100000];

    public static void main(String[] args) {
        dp[0] = 1;
        int n = 5;
        for (int i = 0; i <= 5; i++) {
            System.out.println(factorial(i));
        }
    }

    static long factorial(int n) {
        if (n == 0) {
            return 1;
        }
        else {
            if (dp[n] != 0) {
                return dp[n];
            }
            else {
                dp[n]= prodMOD(n, factorial(n - 1));
                return dp[n];
            }
        }
    }
}

package com.aadavan.dsa.dp.pre;

import java.util.Scanner;

public class Binomial {
    static int MOD = (int)1e9+7;
    static long sumMod(long a, long b) {
        return (a%MOD + b%MOD)%MOD;
    }
    static Scanner sc = new Scanner(System.in);
    static int number = 1001;
    static Long dp[][]= new Long[number][number];
    public static void main(String[] args)
    {
        int t = sc.nextInt();
        while(t-- != 0) {
            int n = sc.nextInt();
            int r = sc.nextInt();
            System.out.println(binomial(n, r));
        }
    }
    static long binomial(int n, int r) {
        if (r == 0 || n == r)
            return 1l;
        if (dp[n][r] != null) {
            return dp[n][r];
        }
        dp[n][r] = sumMod(binomial(n-1, r), binomial(n-1, r-1));
        return dp[n][r];
    }
}

package com.aadavan.dsa.dp.pre;

import java.util.Scanner;

public class Tribonacci3 {
    static int MOD = (int)1e9+7;
    static long sumMOD (long a, long b) {
        return (a%MOD + b%MOD)%MOD;
    }
    static long dp[] = new long[1001];
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        dp[0]=dp[1]=0;
        dp[2]=1;
        //System.out.println(tribonacci(6)); // for debugging
        /*
        int t = sc.nextInt();
        while(t-- != 0) {
            int n = sc.nextInt();
            System.out.println(tribonacci(n));
        }
        */
    }
    static long tribonacci(int n) {
        if (n == 0 || n == 1) {
            return dp[n];
        }
        if (dp[n] != 0 && n > 1) {
            return dp[n];
        }
        dp[n] = sumMOD(sumMOD(tribonacci(n-1) , tribonacci(n-2)) , tribonacci(n-3));
        return dp[n];
    }
}

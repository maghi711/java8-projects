package com.aadavan.check.dsa.dp.counting;

import java.util.Scanner;

public class TwoCrossNProblem {
    static int MOD = (int)1e9+7;
    static long sumMOD(long a, long b){
        return (a%MOD + b%MOD)%MOD;
    }
    static Long dp[] = new Long[(int)1e6+1];
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        int t = sc.nextInt();
        dp[1] = 1l;
        dp[2] = 2l;
        //precompute();
        while(t-- != 0) {
            int n = sc.nextInt();
            System.out.println(twoCrossTwo(n));
        }
    }

    static long twoCrossTwo(int n) {
        if (n < 3) return dp[n];
        if (dp[n] == 0) {
            dp[n] = sumMOD(dp[n-1], dp[n-2]);
        }
        return dp[n];
    }

    static void precompute() {
        for (int i = 3; i <dp.length; i++) {
            dp[i] = sumMOD(dp[i-1],dp[i-2]);
        }
    }

}

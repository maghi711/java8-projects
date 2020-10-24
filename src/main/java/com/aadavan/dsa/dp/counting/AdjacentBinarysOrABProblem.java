package com.aadavan.dsa.dp.counting;

import java.util.Scanner;

public class AdjacentBinarysOrABProblem {
    static int MOD = (int)1e9+7;
    static long sumMOD(long a, long b){
        return (a%MOD + b%MOD)%MOD;
    }
    static long dp[] = new long[(int)1e6+1];
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        int t = sc.nextInt();
        dp[1] = 2l;
        dp[2] = 3l;
        precompute();
        while(t-- != 0) {
            int n = sc.nextInt();
            System.out.println(dp[n]);
        }
    }

    static void precompute() {
        for (int i = 3; i <=dp.length; i++) {
            dp[i] = sumMOD(dp[i-1],dp[i-2]);
        }
    }
}

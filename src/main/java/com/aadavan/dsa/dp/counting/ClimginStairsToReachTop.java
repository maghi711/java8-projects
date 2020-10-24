package com.aadavan.dsa.dp.counting;

import java.util.Scanner;

public class ClimginStairsToReachTop {
    int MOD = (int)1e9+7;
    long sumMOD(long a, long b){
        return (a%MOD + b%MOD)%MOD;
    }
    long prodMOD(long a, long b){
        return (a%MOD * b%MOD)%MOD;
    }
    long dp[] = new long[101];
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        ClimginStairsToReachTop t2 = new ClimginStairsToReachTop();
        t2.precompute();
        while (sc.hasNext()) {
            int n = sc.nextInt();
            System.out.println(t2.solution(n));
        }
    }
    void precompute() {
        if (dp[1] == 0) {
            dp[0] = 0l;
            dp[1] = 1l;
            dp[2] = 2l;
            for (int i = 3; i <dp.length; i++) {
                dp[i] = sumMOD(dp[i - 1], dp[i - 2]);
            }
        }
    }
    long solution(int n) {
        return dp[n];
    }
}

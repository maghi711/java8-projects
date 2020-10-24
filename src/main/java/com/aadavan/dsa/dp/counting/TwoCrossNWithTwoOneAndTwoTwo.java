package com.aadavan.dsa.dp.counting;

import java.util.Scanner;

public class TwoCrossNWithTwoOneAndTwoTwo {
    int MOD = (int)1e9+7;
    long sumMOD(long a, long b){
        return (a%MOD + b%MOD)%MOD;
    }
    long prodMOD(long a, long b){
        return (a%MOD * b%MOD)%MOD;
    }
    long dp[] = new long[251];
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        TwoCrossNWithTwoOneAndTwoTwo t2 = new TwoCrossNWithTwoOneAndTwoTwo();
        t2.precompute();
        while (sc.hasNext()) {
            int n = sc.nextInt();
            System.out.println(t2.solution(n));
        }
    }
    void precompute() {
        dp[0] = 1l;
        dp[1] = 1l;
        dp[2] = 3l;
        dp[3] = 5l;
        for (int i = 4; i <dp.length; i++) {
                dp[i] = sumMOD(dp[i - 1], prodMOD(2, dp[i - 2]));
        }
    }
    long solution(int n) {
        return dp[n];
    }
}

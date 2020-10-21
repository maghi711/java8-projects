package com.aadavan.check.dsa.dp.counting;

import java.util.Scanner;

public class CountNumberWithDice {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        BeingZeroDice bz = new BeingZeroDice();
        int t = sc.nextInt();

        while(t--!=0)
        {
            int n = sc.nextInt();
            System.out.println(bz.countWaysToRepresentNSumOf124(n));
        }
    }

}

class BeingZeroDice {

    int MOD = (int)1e9+7;
    long sumMOD(long a, long b){
        return (a%MOD + b%MOD)%MOD;
    }
    long dp[] = new long[(int)1e5+1];
    void precompute() {
        dp[1] = 1l;
        dp[2] = 2l;
        dp[3] = 4l;
        dp[4] = 8l;
        dp[5] = 16l;
        dp[6] = 32l;
        for (int i = 7; i <dp.length; i++) {
            dp[i] = sumMOD(sumMOD(sumMOD(sumMOD(sumMOD(dp[i-1],dp[i-2]), dp[i-3]), dp[i-4]), dp[i-5]), dp[i-6]);
        }
    }
    {
        precompute();
    }
    long countWaysToRepresentNSumOf124(int n)
    {
        return dp[n];
    }
}

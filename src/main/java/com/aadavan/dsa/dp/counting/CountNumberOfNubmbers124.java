package com.aadavan.dsa.dp.counting;

import java.util.Scanner;

public class CountNumberOfNubmbers124 {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        BeingZero124 bz = new BeingZero124();
        int t = sc.nextInt();

        while(t--!=0)
        {
            int n = sc.nextInt();
            System.out.println(bz.countWaysToRepresentNSumOf124(n));
        }
    }

}
class BeingZero124 {

    int MOD = (int)1e9+7;
    long sumMOD(long a, long b){
        return (a%MOD + b%MOD)%MOD;
    }
    long dp[] = new long[100];
    void precompute() {
        dp[1] = 1l;
        dp[2] = 2l;
        dp[3] = 3l;
        dp[4] = 6l;
        for (int i = 5; i <dp.length; i++) {
            dp[i] = sumMOD(sumMOD(dp[i-1],dp[i-2]), dp[i-4]);
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

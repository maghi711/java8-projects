package com.aadavan.dp;

/**
 * Thursday 1, Sep 2022
 */



public class _1_DpTechniques {
    public static void main(String[] args) {
        int n = 5;
        int dp[]= new int[n + 10];
        System.out.println(solveRecursion(n));
        System.out.println(solveWithRecursionSave(n, dp));
        System.out.println(solveWithTable(n));
    }

    private static int solveRecursion(int n) {
        if (n == 0) return 1;
        if (n == 1) return 2;
        if (n == 2) return 3;
        return solveRecursion(n - 1) + 2 * solveRecursion(n - 2) + solveRecursion(n - 3);
    }

    private static int solveWithRecursionSave(int n, int[] dp) {

        // Base cases
        if(n < 3) dp[n] = n + 1;

        // Already computed result
        if (dp[n] != 0) return dp[n];

        // Compute final result
        int res = solveWithRecursionSave(n-1, dp)
                + 2 * solveWithRecursionSave(n-2, dp)
                + solveWithRecursionSave(n-3, dp);
        dp[n] = res;
        return res;
    }

    private static int solveWithTable(int n) {
        int[] dp = new int[n + 10];

        // Base cases
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 3;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + 2 * dp[i-2] + dp[i-3];
        }
        return dp[n];
    }
}

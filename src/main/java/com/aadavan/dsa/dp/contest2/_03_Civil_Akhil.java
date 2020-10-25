package com.aadavan.dsa.dp.contest2;
import java.util.*;

public class _03_Civil_Akhil {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // System.out.println();
        int n = sc.nextInt();
        int[][] a = new int[n][3];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < 3; j++)
                a[i][j] = sc.nextInt();
        System.out.println(getTotal(a, n));
    }

    private static long getTotal(int[][] a, int n) {
        if (n == 1)
            return Math.min(a[0][0], Math.min(a[0][1], a[0][2]));
        long[][] dp = new long[n][3];
        for (int j = 0; j < 3; j++)
            dp[n - 1][j] = a[n - 1][j];
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                if (j == 0)
                    dp[i][j] = a[i][j] + Math.min(dp[i + 1][j + 1], dp[i + 1][j + 2]);
                else if (j == 1)
                    dp[i][j] = a[i][j] + Math.min(dp[i + 1][j - 1], dp[i + 1][j + 1]);
                else
                    dp[i][j] = a[i][j] + Math.min(dp[i + 1][j - 1], dp[i + 1][j - 2]);
            }
        }
        return Math.min(dp[0][0], Math.min(dp[0][1], dp[0][2]));
    }
}

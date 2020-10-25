package com.aadavan.dsa.dp.contest2;

import java.util.*;

public class _03_Civil_VenkatGry {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] a = new int[n][n];
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                a[i][j] = sc.nextInt();
        //DP Array
        int[][] dp = new int[n+1][n+1];
        //Fill last column
        for(int i=n-1;i>=0;i--)
            dp[i][n-1] = a[i][n-1];
        int min_cost=0;
        for(int j=n-2;j>=0;j--)
            for(int i=n-1;i>=0;i--) {
                dp[i][j] = a[i][j]+Math.min(dp[(i+1)%n][j+1], dp[(i+2)%n][j+1]);
                if(j==0) {
                    if(i==(n-1))min_cost = dp[i][j];
                    else min_cost = Math.min(min_cost, dp[i][j]);
                }
            }
        System.out.println(min_cost);

    }
}

package com.aadavan.check.dsa.dp.grid;

import java.util.*;

/**
 * BASE CASE if you start at 0,0
 * if (i == r-1 || j == c-1) return a[i][j];
 *
 * BOUNDARY CASE
 * if (i < 0 || j > 0 || i>=r || j >=c) return Integer.MAX_VALUE
 */
public class MinCost2DMatrix {
    static Scanner sc = new Scanner(System.in);
    static int mat[][];
    static Integer dp[][];
    static int r;
    static int c;

    int minCost(int i, int j) {
        // BOUNDARY CASE
        if (i < 0 || j < 0 || i >= r || j >= c)
            return Integer.MAX_VALUE;
        // BASE CASE
        if (i == r-1 && j == c-1) {
            return mat[i][j];
        }
        // recursive or transition formula
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        return dp[i][j] = mat[i][j] + Math.min(minCost(i+1, j), minCost(i, j+1));
    }

    int minCost(int a[][]) {
        mat = a;
        r = mat.length;
        c = mat[0].length;
        dp = new Integer[r][c];
        return minCost(0, 0);
    }

    int minCostTry(int a[][]) {
        System.out.print("(");
        int totalCost=0;
        int temp=0;
        temp = a[0][0];
        System.out.print(temp + " -->");
        for(int i=1;i<a.length;i++) {
            for(int j=1;j<a[0].length;j++) {
                int min = Math.min(a[i][j-1],a[i-1][j]);
                totalCost = temp + min;
                System.out.print(min + " -->");
                break;
            }
        }
        System.out.print(")");
        return totalCost;
    }

    public static void main(String[] args)
    {
        MinCost2DMatrix bz = new MinCost2DMatrix();
        int t = sc.nextInt();
        while(t--!=0){
            int m = sc.nextInt();
            int n = sc.nextInt();
            int a[][] = new int[m][n];
            int i, j;
            for(i=0;i<m;i++)
                for(j=0;j<n;j++)
                    a[i][j] = sc.nextInt();
            System.out.println(bz.minCost(a));
        }
    }
}

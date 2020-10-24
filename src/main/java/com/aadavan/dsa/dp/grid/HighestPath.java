package com.aadavan.dsa.dp.grid;

import java.util.Scanner;

public class HighestPath {
    static Scanner sc = new Scanner(System.in);
    static int[][] values;
    public static void main(String[] args) {
        int t = sc.nextInt();
        while(t-- != 0) {
            int row = sc.nextInt();
            int col = sc.nextInt();
            int numBlocked = sc.nextInt();
            int bi = sc.nextInt();
            int bj = sc.nextInt();
            values = new int[row][col];
            values[bi][bj]=-1;
            //System.out.println(countPaths(row, col));
            System.out.println(countPaths(values));
        }
    }

    static long countPaths(int[][] m) {
        int r = m.length;
        int c = m.length;
        int[][] dp = new int[r][c];
        // Fill all first row
        for(int i=0; i<r;i++) {
            if (values[i][0] == 0)
                dp[i][0] = 1;
            else
                dp[i][0] = 0;
        }
        // Fill all firt column
        for (int j=0; j<c; j++) {
            if(values[0][j] == 0)
                dp[0][j] = 1;
            else
                dp[0][j] = 0;
        }
        // Now handle the remaining row and columns
        for (int i = 1; i<r; i++) {
            for (int j = 1; j<c; j++) {
                if (m[i][j] == -1) {
                    dp[i][j] = 0;
                }
                else {
                    dp[i][j]=dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[r-1][c-1];
    }
}

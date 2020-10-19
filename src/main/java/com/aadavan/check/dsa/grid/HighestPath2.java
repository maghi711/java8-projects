package com.aadavan.check.dsa.grid;

import java.util.Arrays;
import java.util.Scanner;

public class HighestPath2 {
    long countPaths(int m[][]) {
        int r = m.length;
        int c = m[0].length;
        int[][] dp = new int[r][c];
        // Fill all first row
        for(int i=0; i<r;i++) {
            if (m[i][0] == 1)
                dp[i][0] = 1;
            else {
                dp[i][0] = 0;
                break;
            }
        }
        // Fill all firt column
        for (int j=0; j<c; j++) {
            if(m[0][j] == 1)
                dp[0][j] = 1;
            else {
                dp[0][j] = 0;
                break;
            }
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        HighestPath2 obj = new HighestPath2();
        while (t-- != 0) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            int b = sc.nextInt();
            int m[][] = new int[r][c];
            for(int i=0;i<r;i++)
                Arrays.fill(m[i], 1);
            for(int i=1;i<=b;i++)
            {
                int x = sc.nextInt();
                int y = sc.nextInt();
                m[x][y] = -1;
            }
            System.out.printf("%d\n", obj.countPaths(m));
        }
    }
}

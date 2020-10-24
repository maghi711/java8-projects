package com.aadavan.dsa.dp.grid;

import java.util.Scanner;

public class TollGateProblem {
    int mat[][];
    int r;
    int c;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t--!=0){
            int m = sc.nextInt();
            int n = sc.nextInt();
            int a[][] = new int[m][n];
            int i, j;
            TollGateProblem bz = new TollGateProblem();
            for(i=0;i<m;i++)
                for(j=0;j<n;j++)
                    a[i][j] = sc.nextInt();
            System.out.println(bz.minCostOfTollTravel(a, 0));
        }

    }

    private boolean minCostOfTollTravel(int cost[][], int startLane) {
        int[][] dp = cost;
        mat = cost;
        int i = cost.length;
        int j = cost[0].length;
        r = i;
        c = j;
        minCost(i, j);
        return true;
    }

    boolean minCostOfTollTravel2(int m[][], int startLane) {
        int r = m.length;
        int c = m[0].length;
        int[][] dp = new int[r][c];
        // Fill all first column
        for (int j=0; j<c; j++) {
            if(m[r-1][j] == 1)
                dp[r-1][j] = 1;
            else {
                dp[r-1][j] = 0;
                break;
            }
        }
        return true;
    }

    private int minCost(int i, int j) {
        // BOUNDARY CASE
        if (i < 0 || j < 0 || i >= r || j >= c)
            return Integer.MAX_VALUE;
        // BASE CASE
        if (j == c-1) {
            return mat[i][j];
        }
        return mat[i][j] = mat[i][j] + Math.min(minCost(i+1, j+1), Math.min(minCost(i-1,j+1), minCost(i, j+1)));
    }
}

/*
* 	static int r;
	static int c;
    static int mat[][];
    public boolean minCostOfTollTravel(int cost[][], int startLane) {
        mat = cost;
        int i = cost.length;
        int j = cost[0].length;
        r = i;
        c = j;
        mat = cost;
        minCost(i, j);
        return true;
    }

    private int minCost(int i, int j) {
        // BOUNDARY CASE
        if (i < 0 || j < 0 || i >= r || j >= c)
            return Integer.MAX_VALUE;
        // BASE CASE
        if (j == c-1) {
            return mat[i][j];
        }
        return mat[i][j] = mat[i][j] + Math.min(minCost(i+1, j+1), Math.min(minCost(i-1,j+1), minCost(i, j+1)));
    }
* */

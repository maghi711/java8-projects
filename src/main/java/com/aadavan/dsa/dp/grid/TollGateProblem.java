package com.aadavan.dsa.dp.grid;

import java.util.Arrays;
import java.util.Scanner;

public class TollGateProblem {
    int mat[][];
    int dp[][];
    int r;
    int c;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- != 0){
            int m = sc.nextInt();
            int n = sc.nextInt();
            int a[][] = new int[m][n];
            int i, j;
            TollGateProblem bz = new TollGateProblem();
            for(i=0;i<m;i++)
                for(j=0;j<n;j++)
                    a[i][j] = sc.nextInt();
            for (int[] x: a) {
                System.out.println(Arrays.toString(x));
            }
            /*
            for (int x=0; x <a.length; x++) {
                System.out.println(bz.minCostOfTollTravel(a, x));
            }
            */
            System.out.println(bz.minCostOfTollTravelBottomUp(a, 1));
        }

    }

    private int minCostOfTollTravelBottomUp(int cost[][], int startLane) {
        mat = cost;
        dp = new int[cost.length+1][cost[0].length+1];

        for (int i = 0; i < cost.length-1; i++) {
            for (int j = 0; j < cost[0].length-1; j++) {
                if (i == 0) {
                    mat[i][j] = mat[i][j];
                } else {
                    //mat[i][j] = mat[i][j] + Math.min(mat[i][j + 1],mat[i-1][j+1]), mat[i+1];
                }
            }
        }
        return 1;
    }

    private int minCostOfTollTravel(int cost[][], int startLane) {
        mat = cost;
        dp = mat;
        int i = cost.length;
        int j = cost[0].length;
        r = i;
        c = j;
        minCost(startLane-1, 0);
        for (int[] x: dp) {
            System.out.println(Arrays.toString(x));
        }
        return dp[startLane-1][0];
    }

    int minCostOfTollTravel2(int m[][], int startLane) {
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
        return minCost(r, c);
    }

    private int minCost(int i, int j) {
        // BOUNDARY CASE
        if (i < 0 || j < 0 || i >= r || j >= c)
            return Integer.MAX_VALUE;
        // BASE CASE
        if (j == c-1) {
            return dp[i][j]=mat[i][j];
        }
        return dp[i][j]=mat[i][j] = mat[i][j] + Math.min(minCost(i+1, j+1), Math.min(minCost(i-1,j+1), minCost(i, j+1)));
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

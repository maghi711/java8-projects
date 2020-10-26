package com.aadavan.dsa.dp.knapsack;

import java.util.Arrays;
import java.util.Scanner;

public class _1_ScubaDiver_Vjudge {
    static int[] oArray = new int[1001];
    static int[] nArray = new int[1001];
    static int[] nWeight = new int[1001];

    static int[][][] dp = new int[22][80][1001];
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while(t-- != 0) {
            int oxy = scanner.nextInt();
            int nit = scanner.nextInt();
            int weight = scanner.nextInt();
            int a[][] = new int[weight][3];
            for (int i = 0; i < weight; i++) {
                for (int j = 0; j < 3; j++) {
                    a[i][j] = scanner.nextInt();
                }
            }
            for (int i = 0; i < weight; i++) {
                oArray[i] = a[i][0];
            }
            for (int i = 0; i < weight; i++) {
                nArray[i] = a[i][1];
            }
            for (int i = 0; i < weight; i++) {
                nWeight[i] = a[i][2];
            }
            for (int[][] ab : dp) {
                for (int[] abc : ab) {
                    Arrays.fill(abc, -1);
                }
            }
            System.out.println(minWeight(oxy, nit, weight));
        }
    }

    static int minWeight(int oxygen, int nitrogen, int noOfCylinders) {
        if (oxygen <= 0 && nitrogen <= 0) {
            return 0;
        }
        if (noOfCylinders <= 0) {
            return 99999;
        }
        if (dp[oxygen][nitrogen][noOfCylinders] != -1) {
            return dp[oxygen][nitrogen][noOfCylinders];
        }
        int take = minWeight(Math.max(0,oxygen-oArray[noOfCylinders-1]), Math.max(0, nitrogen-nArray[noOfCylinders-1]), noOfCylinders-1) + nWeight[noOfCylinders-1];
        int leave = minWeight(oxygen, nitrogen, noOfCylinders-1);
        return dp[oxygen][nitrogen][noOfCylinders] = Math.min(take, leave);
        //return dp[oxygen][nitrogen][noOfCylinders] = Math.min(minWeight(Math.max(0,oxygen-oArray[noOfCylinders-1]), Math.max(0, nitrogen-nArray[noOfCylinders-1]), noOfCylinders-1) + nWeight[noOfCylinders-1], minWeight(oxygen, nitrogen, noOfCylinders-1));
    }
}

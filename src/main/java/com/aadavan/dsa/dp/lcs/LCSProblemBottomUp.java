package com.aadavan.dsa.dp.lcs;

import java.util.Scanner;

/**
 * Longest subsequence problem. LCS LIS
 * https://ideone.com/9PGA4S - Link for Bottom Up approach.
 */
public class LCSProblemBottomUp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = Integer.parseInt(scanner.nextLine());
        while (t-- != 0) {
            String st1 = scanner.nextLine();
            String st2 = scanner.nextLine();
            System.out.println(lcsLen(st1.toCharArray(), st2.toCharArray()));
        }
    }
    static int[][] dp = new int[101][101];
    static int lcsLen(char[] a, char[] b) {
        int aLength = a.length;
        int bLength = b.length;
        /*
        // Fill all columns with 0
        for (int i = 0; i < aLength; i++) {
            dp[i][0] = 0;
        }
        // Fill all rows with 0
        for (int i = 0; i < bLength; i++) {
            dp[0][i] = 0;
        }
        */
        // Print the matrix to check
        printDpArray(aLength, bLength);

        // Logic
        for (int i = 1; i <= aLength; i++) {
            for (int j = 1; j<= bLength; j++) {
                if (a[i-1] == b[j-1]) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        // Print the matrix to check
        printDpArray(aLength, bLength);

        return dp[aLength][bLength];
    }

    private static void printDpArray(int aLength, int bLength) {
        // Print the matrix to check
        for (int i = 0; i <= aLength; i++) {
            for (int j = 0; j <= bLength; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

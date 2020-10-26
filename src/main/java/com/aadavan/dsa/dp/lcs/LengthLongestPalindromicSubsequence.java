package com.aadavan.dsa.dp.lcs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class LengthLongestPalindromicSubsequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = Integer.parseInt(scanner.nextLine());
        while (t-- != 0) {
            String st = scanner.nextLine();
            System.out.println(lpsLen(st.toCharArray()));
        }
    }

    static int[] b;
    static int[][] dp = new int[1001][1001];
    private static int lpsLen(char[] a) {
        char[] b = new char[a.length];
        System.arraycopy(a, 0, b, 0, a.length);
        System.out.println("Arrays.toString(b) = " + Arrays.toString(b));
        reverse(b);
        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= b.length; j++) {
                if (a[i-1] == b[j-1]) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        printDpArray(a.length, b.length);
        return dp[a.length][b.length];
    }

    private static void printDpArray(int aLength, int bLength) {
        // Print the matrix to check
        for (int i = 0; i <= bLength; i++) {
            for (int j = 0; j <= aLength; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    static void reverse(char[] arr) {
        char temp;
        for( int i = 0; i < arr.length/2; ++i )
        {
            temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }
}
/*
    int lpsLen(char a[]) {
        int[][] dp = new int[1001][1001];
        char[] b = new char[a.length];
        System.arraycopy(a, 0, b, 0, a.length);
        reverse(b);
        for (int i = 1; i <= a.length; i++) {
          for (int j = 1; j <= b.length; j++) {
            if (a[i-1] == b[j-1]) {
              dp[i][j] = 1 + dp[i-1][j-1];
            } else {
              dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
          }
        }
        return dp[a.length][b.length];
    }

    void reverse(char[] arr) {
        char temp;
        for( int i = 0; i < arr.length/2; ++i )
        {
            temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }
*/
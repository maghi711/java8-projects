package com.aadavan.dsa.dp.lcs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class LengthLongestIncreasingSubsequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- != 0) {
            int length = scanner.nextInt();
            int a[] = new int[length];
            for (int i = 0; i < length; i++) {
                a[i] = scanner.nextInt();
            }
            System.out.println(longestLISLength(a));
        }
    }

    static int[] b;
    static int[][] dp = new int[1001][1001];
    private static int longestLISLength(int[] a) {
        HashSet<Integer> set = new HashSet<>();
        for(int v: a) {
            set.add(v);
        }
        b = new int[set.size()];
        Iterator<Integer> value = set.iterator();
        int val = 0;
        while (value.hasNext()) {
            Integer next = value.next();
            b[val++] = next;
        }
        Arrays.sort(b);
        System.out.println("Arrays.toString(b) = " + Arrays.toString(b));
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
}
/*

int longestLISLength(int a[]) {
  	int[][] dp = new int[1001][1001];
    HashSet<Integer> set = new HashSet<>();
    for(int v: a) {
      set.add(v);
    }
    int[] b = b = new int[set.size()];
    Iterator<Integer> value = set.iterator();
    int val = 0;
    while (value.hasNext()) {
      Integer next = value.next();
      b[val++] = next;
    }
    Arrays.sort(b);

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
 */
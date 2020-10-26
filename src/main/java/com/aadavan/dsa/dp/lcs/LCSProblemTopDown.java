package com.aadavan.dsa.dp.lcs;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Longest subsequence problem. LCS LIS
 */
public class LCSProblemTopDown {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = Integer.parseInt(scanner.nextLine());
        while (t-- != 0) {
            String st1 = scanner.nextLine();
            String st2 = scanner.nextLine();
            System.out.println(lcsLen(st1.toCharArray(), st2.toCharArray()));
        }
    }
    static char[] ga;
    static char[] gb;
    static int[][] dp = new int[101][101];
    static int lcsLen(char a[], char b[]) {
        int an = a.length;
        int bn = b.length;
        ga = a;
        gb = b;
        for(int[] abb: dp) {
            Arrays.fill(abb, -1);
        }
        return lcsLen(an, bn);
    }
    static int lcsLen(int aLen, int bLen) {
        if (aLen < 0 || bLen < 0) return 0;
        if (aLen == 0 || bLen == 0) return 0;
        if (dp[aLen][bLen] != -1)
            return dp[aLen][bLen];
        if (ga[aLen-1] == gb[bLen-1])
            return dp[aLen][bLen] = 1 + lcsLen(aLen - 1, bLen - 1);
            return dp[aLen][bLen] = Math.max(lcsLen(aLen - 1, bLen),lcsLen(aLen, bLen - 1));
    }
}

/*
// Submitted code - Top Down logic
char[] ga;
char[] gb;
int[][] dp = new int[101][101];
int lcsLen(char a[], char b[])
{
	int an = a.length;
	int bn = b.length;
  	ga = a;
  	gb = b;
  	for(int[] ab: dp) {
      Arrays.fill(ab, -1);
    }
  	return lcsLen(an, bn);
}
int lcsLen(int aLen, int bLen) {
	if (aLen < 0 || bLen < 0) return 0;
        if (aLen == 0 || bLen == 0) return 0;
        if (dp[aLen][bLen] != -1)
            return dp[aLen][bLen];
        if (ga[aLen-1] == gb[bLen-1])
            return dp[aLen][bLen] = 1 + lcsLen(aLen - 1, bLen - 1);
            return dp[aLen][bLen] = Math.max(lcsLen(aLen - 1, bLen),lcsLen(aLen, bLen - 1));
}

 */

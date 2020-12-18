package com.aadavan.dsa.dp.string;

import java.util.Arrays;
import java.util.Scanner;

public class IsInterleaving {
    static char[] s1;
    static char[] s2;
    static char[] s3;
    static int l1;
    static int l2;
    static int l3;
    static final int dpArray = 1001;
    static boolean[][] dp = new boolean[dpArray][dpArray];
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nT = scanner.nextLine();
        int t = Integer.parseInt(nT);
        while (t-- != 0) {
            String value = scanner.nextLine();
            String[] s = value.split(" ");
            s1 = s[0].toCharArray();
            s2 = s[1].toCharArray();
            s3 = s[2].toCharArray();
            l1 = s1.length;
            l2 = s2.length;
            l3 = s3.length;
            System.out.println(Arrays.toString(s1) + " - " + Arrays.toString(s2) + " - " +  Arrays.toString(s3));
            if ((l1 + l2) != l3) {
                System.out.println("false");
                continue;
            }
            System.out.println(isInterleaving(0,0));
        }
    }

    static boolean isInterleaving(int i, int j) {
        int k = i + j;
        if (k == l3)
            return true;
        if(dp[i][j]) {
            return dp[i][j];
        }
        boolean ans = false;
        System.out.println("i = " + i + "\tj = " + j + "\tk = " + k);
        if (i<s1.length && s1[i] == s3[k]) {
            ans |= isInterleaving(i+1, j);
        }
        if (j<s2.length && s2[j] == s3[k]) {
            ans |= isInterleaving(i, j+1);
        }
        return dp[i][j]=ans;
    }
}

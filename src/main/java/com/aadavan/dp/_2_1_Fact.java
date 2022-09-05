package com.aadavan.dp;
import java.util.*;
import java.lang.*;
import java.io.*;

public class _2_1_Fact {

}

class Solution
{
    private static final long M= 100_00_00_00_7;

    static int n = 100_00_00;
    static long dp[] = new long[n + 10];
    static {
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = ((i % M) * (dp[i-1] % M)) % M;
        }
    }

    public static void main (String[] args) throws java.lang.Exception
    {
        /*
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- != 0) {
            int val = sc.nextInt();
            System.out.println((dp[val]%M));
        }
        */

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            final String inputTimes = bufferedReader.readLine();
            int t = Integer.parseInt(inputTimes);
            System.out.println(t);
            while (t-- != 0) {
                int val = Integer.parseInt(bufferedReader.readLine());
                System.out.println((dp[val]%M));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void reader() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            final String inputTimes = bufferedReader.readLine();
            int t = Integer.parseInt(inputTimes);
            System.out.println(t);
            while (t-- != 0) {
                int val = Integer.parseInt(bufferedReader.readLine());
                System.out.println((dp[val]%M));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

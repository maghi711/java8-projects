package com.aadavan.dp;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Solution_2
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
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            final String inputTimes = bufferedReader.readLine();
            int t = Integer.parseInt(inputTimes);
            while (t-- != 0) {
                int val = Integer.parseInt(bufferedReader.readLine());
                bufferedWriter.write((dp[val]%M) + "\n");
            }
            bufferedWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

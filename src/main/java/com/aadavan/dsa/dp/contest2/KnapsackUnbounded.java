package com.aadavan.dsa.dp.contest2;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class KnapsackUnbounded {
    // Complete the unboundedKnapsack function below.
    static int unboundedKnapsack(int k, int[] arr) {
        int n = arr.length;
        int dp[] = new int[2001];
        for(int i=0; i<=k; i++) {
            for (int j=0; j<n; j++) {
                if(arr[j]<=i) dp[i]=Math.max(dp[i], dp[i-arr[j]] + arr[j]);
            }
        }
        return dp[k];
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        int t = scanner.nextInt();
        while (t-- != 0) {
            int size = scanner.nextInt();
            int capacity = scanner.nextInt();
            int arr[] = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = scanner.nextInt();
            }
            int result = unboundedKnapsack(capacity, arr);
            System.out.println(result);
        }
    }
}

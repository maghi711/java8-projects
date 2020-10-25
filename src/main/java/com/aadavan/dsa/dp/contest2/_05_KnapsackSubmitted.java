package com.aadavan.dsa.dp.contest2;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class _05_KnapsackSubmitted {
    // Complete the unboundedKnapsack function below.
    static int unboundedKnapsack(int k, int[] arr) {
        int n = arr.length;
        int dp[] = new int[k+2];
        for(int i=0; i<=k; i++) {
            for (int j=0; j<n; j++) {
                if(arr[j]<=i) dp[i]=Math.max(dp[i], dp[i-arr[j]] + arr[j]);
            }
        }
        return dp[k];
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        while (t-- != 0) {
            String[] nk = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nk[0]);

            int k = Integer.parseInt(nk[1]);

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }
            int result = unboundedKnapsack(k, arr);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
        bufferedWriter.close();

        scanner.close();
    }
}

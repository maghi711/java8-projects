package com.aadavan.dsa.dp.knapsack;

import java.util.Arrays;
import java.util.Scanner;

public class Problem_02 {
    static int ROWS = 3001;
    static int COLS = 3001;
    static int dp[][] = new int[ROWS][COLS];
    static ItemSecond[] it;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- != 0) {
            int capacity = sc.nextInt();
            int arraySize = sc.nextInt();
            ItemSecond[] items = new ItemSecond[arraySize];
            // fill capacity
            for (int i = 0; i < arraySize; i++) {
                items[i] = new ItemSecond(0, sc.nextInt());
            }
            // fill values
            for (int i = 0; i < arraySize; i++) {
                items[i].weight = sc.nextInt();
            }
            System.out.println(maxValue(items, capacity));
        }
    }

    static int maxValue(ItemSecond[] items, int capacity) {
        it = items;
        // Init DP array for the top down approach
        for(int[] a: dp) {
            Arrays.fill(a, -1);
        }
        return maxValue(capacity, items.length);
    }

    static int maxValue(int capacity, int size) {
        // TAKE IT OR LEAVE IT
        // BASE and BOUNDARY CASE
        if (capacity <=0 || size <= 0)
            return 0;
        if (it[size-1].weight > capacity) {
            return maxValue(capacity, size-1);
        }
        if (dp[size][capacity] != -1) {
            return dp[size][capacity];
        }
        dp[size][capacity] = Math.max(
          it[size-1].value + maxValue(capacity - it[size-1].weight, size-1),maxValue(capacity, size-1)
        );
        return dp[size][capacity];
    }
}

class ItemSecond {
    int weight;
    int value;

    public ItemSecond(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    @Override
    public String toString() {
        return "weight=" + weight +
               ", value=" + value +
               "\n";
    }
}
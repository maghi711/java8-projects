package com.aadavan.dsa.dp.knapsack;

import java.util.Arrays;
import java.util.Scanner;


public class Problem_01 {
    static int ROWS= 3001;
    static int COLS = 3001;
    static int dp [][] = new int[ROWS][COLS];
    static Item[] it;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- != 0) {
            int capacity = sc.nextInt();
            int arraySize = sc.nextInt();
            Item[] items = new Item[arraySize];
            // init values
            for (int i = 0; i < arraySize; i++) {
                items[i] = new Item(0, sc.nextInt());
            }
            // init weight
            for (int i = 0; i < arraySize; i++) {
                items[i].weight = sc.nextInt();
            }
            System.out.println(maxValue(items, capacity));
        }
    }
    static int maxValue(int capacity, int size) {
        // Base and Boundary case are handled here
        if (capacity <=0 || size <= 0)
            return 0;
        if (it[size-1].weight > capacity) {
            return maxValue(capacity, size-1);
        }
        if (dp[size][capacity] != -1) return dp[size][capacity];
        int size1 = size - 1;
        dp[size][capacity] = Math.max( it[size1].value + maxValue(capacity-it[size1].weight, size1), maxValue(capacity, size1));
        return dp[size][capacity];
    }
    static int maxValueBottomUp(Item[] items, int capacity) {
        return -1;
    }
    static int maxValue(Item[] items, int capacity) {
        // Initialize the 2D array with -1
        for(int[] a : dp) {
            Arrays.fill(a, -1);
        }
        it = items;
        return maxValue(capacity, items.length);
    }
}

class Item {
    int weight;
    int value;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Item{" +
                "weight=" + weight +
                ", value=" + value +
                '}';
    }
}
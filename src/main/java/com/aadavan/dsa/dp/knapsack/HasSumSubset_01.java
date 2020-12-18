package com.aadavan.dsa.dp.knapsack;

import java.util.Arrays;
import java.util.Scanner;

public class HasSumSubset_01 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- != 0) {
            // Number of elements in the array
            int arraySize = sc.nextInt();
            int[] array = new int[arraySize];
            for (int i = 0; i < array.length; i++) {
                array[i] = sc.nextInt();
            }
            // Number of Q sum queries
            int sumQueries = sc.nextInt();
            int sumArray[] = new int[sumQueries];
            for (int i = 0; i < sumQueries; i++) {
                sumArray[i] = sc.nextInt();
            }
            for (int i =0; i<sumQueries;i++) {
                hasSubsetSum(array[i], sumArray[i]);
            }
        }
    }
    static int[] list;
    static boolean hasSubsetSum(int a[], int s){
        list = a;
        return hasSubsetSum(s, a.length);
    }
    static boolean hasSubsetSum(int sum, int size) {
        if (sum == 0) {
            return true;
        }
        if (size <= 0 || sum < 0) {
            return false;
        }
        return hasSubsetSum(sum - list[size-1], size-1) || hasSubsetSum(sum, size-1);
    }
}

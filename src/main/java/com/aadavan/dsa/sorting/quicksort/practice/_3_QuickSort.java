package com.aadavan.dsa.sorting.quicksort.practice;

import java.util.Arrays;

public class _3_QuickSort {

    public static void main(String[] args) {
        int[] n = {5, 4, 3, 2, 1};
        System.out.println("n = " + Arrays.toString(n));
        quickSort(n, 0, n.length-1);
        System.out.println("n = " + Arrays.toString(n));
    }

    private static void quickSort(int[] n, int l, int h) {
        int j = partition(n, l, h);
        quickSort(n, l, j);
        quickSort(n, j + 1, h);
    }

    private static int partition(int[] a, int low, int high) {
        int pivot = a[low];
        int i = low;
        int j = high;
        while (a[i] <= pivot) {
            if (i == high) break;
            i++;
        }
        while (a[j] > pivot) {
            j--;
        }
        if (i < j) {
            swap(a, i, j);
        }
        swap(a, low, j);
        return j;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i]= a[j];
        a[j] = temp;
    }

}

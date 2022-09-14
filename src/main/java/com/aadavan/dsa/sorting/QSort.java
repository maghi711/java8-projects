package com.aadavan.dsa.sorting;

import java.util.Arrays;

public class QSort {
    public static void main(String[] args) {
        int[] numbers = {10, 16, 8, 12, 15, 6, 3, 9, 5};
        System.out.println("numbers = " + Arrays.toString(numbers));
        quickSort(numbers, 0, numbers.length-1);
        System.out.println("numbers = " + Arrays.toString(numbers));
    }

    static void quickSort(int[] a, int low, int high) {
        if (low < high) {
            int j = partition(a, low, high);
            quickSort(a, low, j);
            quickSort(a, j+1, high);
        }
    }

    static int partition(int[] a, int low, int high) {
        int pivot = a[low];
        System.out.println("Pivot --> " + pivot);
        int i = low;
        int j = high;
        while(i < j) {
            while (a[i] <= pivot) {
                i++;
            }
            while (a[j] > pivot) {
                j--;
            }
            if (i < j) {
                swap(a, i, j);
            }
        }
        swap(a, low, j);
        return j;
    }

    static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}

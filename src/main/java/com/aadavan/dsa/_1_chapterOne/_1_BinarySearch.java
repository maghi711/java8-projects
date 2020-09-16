package com.aadavan.dsa._1_chapterOne;

/**
 * Searches an element for a given sorted array.
 * Returns the elements position if found otherwise returns -1
 */
public class _1_BinarySearch {

    public static void main(String[] args) {
        int[] a = {22, 33, 44, 55, 66, 77};
        int searchElement = 44;
        System.out.println("search element (" + searchElement + ") is placed at (" + search(a, 44) + ") position in the array.");
    }

    private static int search(int[] a, int element) {
        if (a == null || a.length ==0) {
            return  -1;
        }
        int low = 0;
        int high = a.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (a[mid] == element) {
                return mid;
            } else if (a[mid] < element) {
                low = mid+1;
            } else {
                high = mid;
            }
        }
        return -1;
    }
}

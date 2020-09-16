package com.aadavan.dsa._1_chapterOne;

public class _2_BinarySearch {
    public static void main(String[] args) {
        int[] a = {12, 15, 17, 20, 25, 30};
        int searchElement = 17;
        final int i = searchSortedArray(a, searchElement);
        if (i != -1) {
            System.out.println("found element at index :" + (i + 1));
        } else {
            System.out.println(searchElement + " not found in the list.");
        }
    }

    /**
     * Searched sorted array using divide and conquer technique
     * @param a an array
     * @param searchElement the element to be searched
     * @return i the index position where the element is available
     */
    public static int searchSortedArray(int[] a, int searchElement) {
        int none = -1;
        if (a == null || a.length == 0) {
            return none;
        }
        int low = 0;
        int high = a.length;
        while(low < high) {
            int mid = (low + high) / 2;
            if (a[mid] == searchElement) {
                return mid;
            } else if (a[mid] < searchElement) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return none;
    }
}

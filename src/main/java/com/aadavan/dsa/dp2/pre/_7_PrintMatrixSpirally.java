package com.aadavan.dsa.dp2.pre;

import java.util.Arrays;

public class _7_PrintMatrixSpirally {

    public static void main(String[] args) {
        int[][] a = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        printMatrixArray(a);
        fillSpirally(a);
        printMatrixArray(a);
    }

    private static void printMatrixArray(int[][] a) {
        for (int[] ab: a) {
            System.out.print(Arrays.toString(ab));
            System.out.println();
        }
        System.out.println();
    }

    static void fillSpirally(int[][] a) {
        /*
            k - starting row index
            m - ending row index
            l - starting column index
            n - ending column index
		*/
        int m = a.length;
        int n = a[0].length;
        int k = 0;
        int l = 0;
        int i;
        int number = 1;
        while (k < m && l < n) {
            for (i = l; i < n; ++i) {
                a[k][i] = number++;
            }
            k++;
            // Print the last column from the remaining
            // columns
            for (i = k; i < m; ++i) {
                a[i][n - 1] = number++;
            }
            n--;
            // Print the last row from the remaining rows */
            if (k < m) {
                for (i = n - 1; i >= l; --i) {
                    a[m - 1][i] = number++;
                }
                m--;
            }

            // Print the first column from the remaining
            // columns */
            if (l < n) {
                for (i = m - 1; i >= k; --i) {
                    a[i][l] = number++;
                }
                l++;
            }
        }
    }
}

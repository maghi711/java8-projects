package com.aadavan.dsa.dp2.pre;

public class _7_FillMatrixSpirally_1 {
    public static void main(String[] args) {
        int[][] a = {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        printMatrix(a);
        fillMatrixSpirally(a);
        printMatrix(a);
    }

    private static void fillMatrixSpirally(int[][] a) {
        int l=0, k=0; // l points to column beginning, and k points to row beginning
        int m = a.length; // row end
        int n = a[0].length; // column end
        int i; // iteration pointer
        int value = 1;

        // Boundary condition
        while(k < m && l < n) {
            // Fill Row
            for (i = l; i < n; i++) {
                a[k][i] = value++;
            }
            k++; // Increment Row

            // Fill Last column
            for(i = k; i < m; i++) {
                a[i][n-1] = value++;
            }
            n--; // Decrement Column

            // Fill Last Row
            if (k < m) {
                for (i = n - 1; i >= l; i--) {
                    a[m - 1][i] = value++;
                }
                m--;
            }

            // Fill first column
            if (l < n) {
                for (i=m-1;i>=k;i--) {
                    a[i][l] = value++;
                }
                l++;
            }
        }
    }

    private static void printMatrix(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}

package com.aadavan.dsa.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrintTopMatrices {
    public static void main(String[] args) {
        /*
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t--!=0){
            int r = sc.nextInt();
            int c = sc.nextInt();

            // Create a List of null objects, where each object is List<Integer>
            List< List<Integer> > mat = new ArrayList<>();

            // It is important to init all one dimentional arrays
            for(int i=0;i<r;i++)
                mat.add(new ArrayList<>());

            for(int i=0;i<r;i++){
                for(int j=0;j<c;j++){
                    int x = sc.nextInt();
                    mat.get(i).add(x);  // Add x in the ith List to the end
                }
            }
            */
        List< List<Integer> > mat = new ArrayList<>(4);
        mat.add(new ArrayList<>(4));
        mat.add(new ArrayList<>(4));
        mat.add(new ArrayList<>(4));
        mat.add(new ArrayList<>(4));

        List<Integer> integers = mat.get(0);
        integers.add(1);
        integers.add(0);
        integers.add(0);
        integers.add(0);

        integers = mat.get(1);
        integers.add(0);
        integers.add(2);
        integers.add(0);
        integers.add(0);

        integers = mat.get(2);
        integers.add(0);
        integers.add(0);
        integers.add(2);
        integers.add(0);

        integers = mat.get(3);
        integers.add(0);
        integers.add(0);
        integers.add(0);
        integers.add(1);
        printMatrix(mat);
        fillUpperTriangularMatrix(mat);
        printMatrix(mat);
        fillUpperTriangularMatrixSecondApproach(mat);
        printMatrix(mat);
    }

    private static void fillUpperTriangularMatrix(List<List<Integer>> mat) {
        int n = mat.size();
        for (int d = 1; d < n; d++) {
            int i = 0, j = d;
            while (j<n) {
                mat.get(i).set(j, mat.get(i + 1).get(j) + mat.get(i).get(j-1));
                i++;
                j++;
            }
        }
    }

    private static void fillUpperTriangularMatrixSecondApproach(List<List<Integer>> mat) {
        int n = mat.size();
        for(int i=n-2; i >=0; i--) {
            for(int j=i+1; j<n; j++) {
                //mat[i][j] = mat[i+1] + mat[i][j-1];
                mat.get(i).set(j, mat.get(i+1).get(j) + mat.get(i).get(j-1));
            }
        }
    }

    static void printMatrix(List< List<Integer> > mat){
        int r = mat.size();        // Get Rows
        int c = mat.get(0).size(); // Get Columns

        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++)
                System.out.printf("%d ", mat.get(i).get(j));  // Get jth element from ith List
            System.out.println();
        }
        System.out.println();
    }

}

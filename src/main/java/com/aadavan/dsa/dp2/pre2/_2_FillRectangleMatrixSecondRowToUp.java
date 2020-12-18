package com.aadavan.dsa.dp2.pre2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _2_FillRectangleMatrixSecondRowToUp {
    public static void main(String[] args) {
        List<List<Integer>> matrix = getMatrix();
        printMatrix(matrix);
        fillSecondRowToTop(matrix);
        printMatrix(matrix);
    }

    private static void fillSecondRowToTop(List<List<Integer>> matrix) {
        for (int i = matrix.size()-2; i >= 0; i--) {
            for (int j = matrix.get(i).size()-2; j >=0 ; j--) {
                int value = Math.max(matrix.get(i+1).get(j), Math.max(matrix.get(i).get(j+1), matrix.get(i+1).get(j+1)));
                matrix.get(i).set(j, value);
            }
        }
    }

    private static void printMatrix(List<List<Integer>> matrix) {
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                System.out.print(matrix.get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static List<List<Integer>> getMatrix1() {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(0, 0, 0, 5));
        matrix.add(Arrays.asList(0, 0, 0, 2));
        matrix.add(Arrays.asList(4, 3, 2, 1));
        return matrix;
    }
    private static List<List<Integer>> getMatrix() {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(0, 0, 1));
        matrix.add(Arrays.asList(0, 0, 2));
        matrix.add(Arrays.asList(2, 1, 3));
        return matrix;
    }
}

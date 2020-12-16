package com.aadavan.dsa.practice._1_dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixBuild {
    public static void main(String[] args) {
        List<List<Integer>> matrix = buildMatrix();
        print(matrix);
        fillTopMatrix(matrix);
        print(matrix);
    }

    private static void fillTopMatrix(List<List<Integer>> matrix) {
        for (int i = matrix.size()-2; i>=0 ; i--) {
            for (int j = matrix.get(i).size()-2; j >= 0 ; j--) {
                if (i < j) {
                    matrix.get(i).set(j, Math.max(matrix.get(i + 1).get(j), Math.max(matrix.get(i).get(j + 1), matrix.get(i + 1).get(j + 1))));
                }
            }
        }
    }

    private static void print(List<List<Integer>> matrix) {
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                System.out.print(matrix.get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static List<List<Integer>> buildMatrix() {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(0, 0, 0, 4));
        matrix.add(Arrays.asList(0, 0, 0, 5));
        matrix.add(Arrays.asList(0, 0, 0, 3));
        matrix.add(Arrays.asList(1, 2, 3, 2));
        return matrix;
    }
}

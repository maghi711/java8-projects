package com.aadavan.dsa.dp2.pre2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _FillRectanglarMatrixSecondRowToUp {
    public static void main(String[] args) {
        List<List<Integer>> matrix = getMatrix();
        printList(matrix);
        fillMatrix(matrix);
        printList(matrix);
    }

    private static void fillMatrix(List<List<Integer>> matrix) {
        for (int i = matrix.size()-2; i >= 0; i--) {
            for (int j = matrix.get(i).size() - 2; j >= 0; j--) {
                int value = Math.max(matrix.get(i+1).get(j), Math.max(matrix.get(i).get(j+1), matrix.get(i+1).get(j+1)));
                matrix.get(i).set(j, value);
            }
        }
    }

    private static void printList(List<List<Integer>> matrix) {
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                System.out.print(matrix.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    private static List<List<Integer>> getMatrix() {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(0, 0, 0, 5));
        matrix.add(Arrays.asList(0, 0, 0, 2));
        matrix.add(Arrays.asList(4, 3, 2, 1));
        return matrix;
    }
}

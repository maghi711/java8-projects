package com.aadavan.dsa.dp2.pre2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _3_FillRectangleTopRowUp {

    public static void main(String[] args) {
        List<List<Integer>> matrix = getMatrix();
        print(matrix);
        fillTopRow(matrix);
        print(matrix);
    }

    private static void fillTopRow(List<List<Integer>> matrix) {
        for (int i = matrix.size()-2; i >=0 ; i--) {
            for (int j = matrix.get(i).size()-2; j >=0 ; j--) {
                int value = Math.max(matrix.get(i).get(j+1),Math.max(matrix.get(i+1).get(j), matrix.get(i+1).get(j+1)));
                matrix.get(i).set(j, value);
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

    private static List<List<Integer>> getMatrix() {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(0,0,0,5));
        matrix.add(Arrays.asList(0,0,0,3));
        matrix.add(Arrays.asList(4,3,2,1));
        return matrix;
    }
}

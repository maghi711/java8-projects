package com.aadavan.dsa.dp2.pre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _6_FillRectamgularMatrixLastSecondRowToTop {
    public static void main(String[] args) {
        List<List<Integer>> list = getList();
        printList(list);
        modifyList(list);
        printList(list);
    }

    private static void modifyList(List<List<Integer>> mat) {
        for(int i = mat.size()-2; i >= 0; i--) {
            for (int j = mat.get(i).size()-2; j >= 0;j--) {
                int value = Math.max(mat.get(i+1).get(j), Math.max(mat.get(i).get(j+1), mat.get(i+1).get(j+1)));
                mat.get(i).set(j, value);
            }
        }
    }

    static List<List<Integer>> getList() {
        List<Integer> row1 = Arrays.asList(0, 0, 0, 5);
        List<Integer> row2 = Arrays.asList(0, 0, 0, 2);
        List<Integer> row3 = Arrays.asList(4, 3, 2, 1);
        List<List<Integer>> result = new ArrayList<>();
        result.add(row1);
        result.add(row2);
        result.add(row3);
        return result;
    }

    static void printList(List<List<Integer>> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                System.out.print(list.get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

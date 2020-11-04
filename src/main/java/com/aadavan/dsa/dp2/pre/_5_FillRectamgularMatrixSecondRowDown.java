package com.aadavan.dsa.dp2.pre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class _5_FillRectamgularMatrixSecondRowDown {
    public static void main(String[] args) {
        List<List<Integer>> list = getList();
        printList(list);
        modifyList(list);
        printList(list);
        
        //System.out.println("getList() = " + list);
    }

    private static void modifyList(List<List<Integer>> mat) {
        for(int i = 1; i< mat.size(); i++) {
            for (int j = 1; j < mat.get(i).size();j++) {
                int value = Math.min(mat.get(i-1).get(j), Math.min(mat.get(i).get(j-1), mat.get(i-1).get(j-1)));
                mat.get(i).set(j, value);
            }
        }
    }

    static List<List<Integer>> getList() {
        List<Integer> row1 = Arrays.asList(4, 3, 2, 1);
        List<Integer> row2 = Arrays.asList(2, 0, 0, 0);
        List<Integer> row3 = Arrays.asList(5, 0, 0, 0);
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

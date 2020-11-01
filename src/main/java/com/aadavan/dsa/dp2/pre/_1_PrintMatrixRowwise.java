package com.aadavan.dsa.dp2.pre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _1_PrintMatrixRowwise {
    public static void main(String[] args) {
        List<List<Integer>> list = getList();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                System.out.print(list.get(i).get(j) + " ");
            }
            System.out.println();
        }
        //System.out.println("getList() = " + list);
    }

    static List<List<Integer>> getList() {
        List<Integer> row1 = Arrays.asList(1, 2, 3);
        List<Integer> row2 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> row3 = Arrays.asList(9, 1);
        List<List<Integer>> result = new ArrayList<>();
        result.add(row1);
        result.add(row2);
        result.add(row3);
        return result;
    }
}

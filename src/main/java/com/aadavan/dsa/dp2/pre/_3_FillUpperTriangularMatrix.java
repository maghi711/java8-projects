package com.aadavan.dsa.dp2.pre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _3_FillUpperTriangularMatrix {
    public static void main(String[] args) {
        List<List<Integer>> list = getList();
        fillUpperTriangularMatrix(list);
        System.out.println("list = " + list);

    }

    static void fillUpperTriangularMatrix(List<List<Integer>> list) {
        int n = list.size();
        for (int i = n-2; i >= 0; i--) {
            //System.out.println(i + " " + list.get(i).get(i));
            for (int j = 0; j <= n-1; j++) {
                if (j >= i) {
                    continue;
                }
                list.get(i).set(j, (list.get(i+1).get(j) + list.get(i).get(j-1)));
                //System.out.println(list.get(i).get(j));
            }
            //System.out.println();
        }
    }

    static List<List<Integer>> getList() {
        List<Integer> row1 = Arrays.asList(1, 0, 0, 0);
        List<Integer> row2 = Arrays.asList(0, 2, 0, 0);
        List<Integer> row3 = Arrays.asList(0, 0, 2, 0);
        List<Integer> row4 = Arrays.asList(0, 0, 0, 1);
        List<List<Integer>> result = new ArrayList<>();
        result.add(row1);
        result.add(row2);
        result.add(row3);
        result.add(row4);
        return result;
    }
}

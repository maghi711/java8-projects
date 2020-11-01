package com.aadavan.dsa.dp2.pre;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _2_ReadDynamicList {

    public static void main(String[] args) {
        List<List<Integer>> list = getList(new Scanner(System.in));
        System.out.println("list = " + list);
    }

    private static List<List<Integer>> getList(Scanner scanner) {
        int listSize = scanner.nextInt();
        List<List<Integer>> finalList = new ArrayList<>();
        System.out.println("listSize = " + listSize);
        for (int i = 0; i < listSize; i++) {
            int subListSize = scanner.nextInt();
            List<Integer> values = new ArrayList<>();
            for (int j = 0; j < subListSize; j++) {
                values.add(scanner.nextInt());
            }
            finalList.add(values);
        }
        return finalList;
    }
}

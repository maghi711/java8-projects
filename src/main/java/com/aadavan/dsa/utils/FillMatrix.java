package com.aadavan.dsa.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FillMatrix {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        final String s = scanner.nextLine();
        long numberOfTests = Long.parseLong(s);
        while(numberOfTests != 0) {
            //System.out.println("Executing iteration " + numberOfTests);
            List<List<Integer>> allValues = constructMatrix();
            fillTopRight(allValues);
            print(allValues);
            numberOfTests--;
        }
    }

    private static List<List<Integer>> constructMatrix() {
        final String s = scanner.nextLine();
        long size = Long.parseLong(s);
        List<List<Integer>> allValues = new ArrayList<>();
        for (int i=0; i < size; i++) {
            List<Integer> value = new ArrayList<>();
            final String s1 = scanner.nextLine();
            final String[] s2 = s1.split(" ");
            for(String s3 : s2) {
                value.add(Integer.parseInt(s3));
            }
            allValues.add(value);
        }
        //System.out.println("allValues = " + allValues);
        return allValues;
    }

    private static void fillTopRight(List<List<Integer>> allValues) {
        int n = allValues.size();
        for (int d=1; d < n; d++) {
            int i = n;
            int j = 0;
            while(i < n && j <n) {
                allValues.get(i).set(j, allValues.get(i).get(j+1) + allValues.get(i-1).get(j));   // JAVA
                i--;
                j++;
            }
            /*
            for (int j=0; j < size; j++) {
                if (j > i) {
                    allValues.get(i).set(j, allValues.get(i).get(j) + allValues.get(i+1).get(j));
                }
            }
             */
            //System.out.println();
        }
    }

    private static void print(List<List<Integer>> allValues) {
        int size = allValues.size();
        for (int i=0; i < size; i++) {
            for (int j=0; j < size; j++) {
                System.out.print(allValues.get(i).get(j));
            }
            System.out.println();
        }
    }
}

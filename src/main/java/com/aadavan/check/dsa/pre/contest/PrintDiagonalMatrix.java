package com.aadavan.check.dsa.pre.contest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrintDiagonalMatrix {
    static Scanner sc= new Scanner(System.in);
    public static void main(String[] args) {
        int t = Integer.parseInt(sc.nextLine());
        while(t-- != 0) {
            int size = Integer.parseInt(sc.nextLine());
            printDiagonal(size);
        }
    }

    private static void printDiagonal(int size) {
        List<List<Integer>> values = readAndReturn(size);
        for (int i=0; i < size; i++) {
            int sum = 0;
            for(int j=i; j<size;j++) {
                List<Integer> first = values.get(j);
                int n = first.size();
                for (int k = 0; k < n-j; k++) {
                    if (k == ((n - j - 1))) {
                        sum = sum + first.get(k);
                        if (i == 0 || i == j) {
                            System.out.print(sum + " ");
                        }
                    }
                }
            }
            System.out.println();
        }
    }

    static List<List<Integer>> readAndReturn(int listSize){
        List<List<Integer>> result = new ArrayList<>();
        for (int i=0; i < listSize; i++) {
            List<Integer> values = new ArrayList<>();
            String str = sc.nextLine();
            final String[] s = str.split(" ");
            for (int j=0; j < s.length; j++) {
                values.add(Integer.parseInt(s[j]));
            }
            result.add(values);
        }
        return result;
    }
}

package com.aadavan.check.dsa;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
    2
    4
    3 1 2 3
    5 1 2 3 4 5
    2 9 1
    1 2
    3
    1 2
    1 3
    1 4
*/
public class ReadAndLoop {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        long iterationCount = Long.parseLong(scanner.nextLine());
        while (iterationCount != 0) {
            System.out.println("Iterating count " + iterationCount);
            readList();
            iterationCount--;
            System.out.println();
        }
    }

    private static void readList() {
        long listLength = Long.parseLong(scanner.nextLine());
        for (int i=0; i < listLength; i++) {
            String readLine = scanner.nextLine();
            String[] a = readLine.split(" ");
            long listSize = Long.parseLong(a[0]);
            List<Integer> list = new ArrayList<>();
            for (int j = 1; j < a.length; j++) {
                int value = Integer.parseInt(a[j]);
                list.add(value);
            }
        }
    }
}

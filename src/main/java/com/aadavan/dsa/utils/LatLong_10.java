package com.aadavan.dsa.utils;

import java.util.*;

public class LatLong_10 {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        long iterationCount = Long.parseLong(scanner.nextLine());
        while (iterationCount != 0) {
            //System.out.println("Iterating count " + iterationCount);
            readList();
            iterationCount--;
            System.out.println();
        }
    }

    private static void readList() {
        String listLength = scanner.nextLine();
        String[] a = listLength.split(" ");
        Long loopOne = Long.parseLong(a[0]);
        Long loopTwo = Long.parseLong(a[1]);
        List<Integer> masterList = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Map<String, String> masterMap = new LinkedHashMap<>();
        for (int j = 0; j < loopOne; j++) {
            String readOne = scanner.nextLine();
            //System.out.println("readOne = " + readOne);
            String[] readOneArray = readOne.split(" ");
            final String name = readOneArray[0];
            final String lat = readOneArray[1];
            final String lng = readOneArray[2];
            masterMap.put(lat + "#" + lng, name);
        }
        //System.out.println("masterMap = " + masterMap);
        for (int j = 0; j < loopTwo; j++) {
            String readOne = scanner.nextLine();
            //System.out.println("readOne = " + readOne);
            String[] readOneArray = readOne.split(" ");
            final String lat = readOneArray[0];
            final String lng = readOneArray[1];
            final String result = masterMap.get(lat + "#" + lng);
            System.out.println((result != null)? result : "-1");
            //getNames(masterMap, readOne);
        }
    }


}

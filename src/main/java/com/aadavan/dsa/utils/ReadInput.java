package com.aadavan.dsa.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadInput {
    public static void main(String[] args) {
        System.out.println("readAndRE = " + readAndReturn(new Scanner(System.in)));
    }

    static List<List<Integer>> readAndReturn(Scanner sc){
        List<List<Integer>> result = new ArrayList<>();
        int listSize = Integer.parseInt(sc.nextLine());
        for (int i=0; i < listSize; i++) {
            List<Integer> values = new ArrayList<>();
            String str = sc.nextLine();
            final String[] s = str.split(" ");
            for (int j=1; j < s.length; j++) {
                values.add(Integer.parseInt(s[j]));
            }
            result.add(values);
        }
        return result;
    }

    static void temp() {
        Scanner sc= new Scanner(System.in); //System.in is a standard input stream
        System.out.print("Enter a string: ");
        String str= sc.nextLine();              //reads string
        final String[] s = str.split(" ");
        List<Integer> values = new ArrayList();
        for (String st: s) {
            values.add(Integer.parseInt(st));
        }
        System.out.print("You have entered: "+str);
        System.out.println("values = " + values);
    }

}

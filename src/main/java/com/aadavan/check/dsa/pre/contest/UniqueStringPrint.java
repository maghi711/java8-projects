package com.aadavan.check.dsa.pre.contest;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class UniqueStringPrint {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        System.out.println("Enter total test cases");
        int t = sc.nextInt();sc.nextLine();
        while(t-- != 0) {
            System.out.println("Enter word length");
            System.out.println(sc.nextLine());
            System.out.println("Enter word");
            String st = sc.nextLine();
            uniqueStrings(st);
        }
        //uniqueStrings("apple is good for health and great for vision too");
    }

    private static void uniqueStrings(String st) {
        Set<String> set = new LinkedHashSet<>();
        final String[] s = st.split(" ");
        for(String val:s) {
            set.add(val);
        }
        StringBuilder finalWord = new StringBuilder();
        for(String val:set) {
            finalWord.append(val);
            finalWord.append(" ");
        }
        final String s1 = finalWord.toString();
        System.out.println(s1.trim());
    }
}

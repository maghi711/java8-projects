package com.aadavan.check;

import java.util.Arrays;

public class AadavanArrayCheck {
    public static void main(String[] args) {
        String answer = pickRandom(new String[] {"yes", "no", "outlook good"});
        System.out.println("my solution = " + answer);
    }

    private static String pickRandom(String[] strings) {
        int maxLength = 0;
        int arrayIndex = 0;
        for (int i=0; i < strings.length; i++) {
            if (strings[i].length() > maxLength) {
                maxLength = strings[i].length();
                arrayIndex = i;
            }
        }
        return strings[arrayIndex];
    }
}

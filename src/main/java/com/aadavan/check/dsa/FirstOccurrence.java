package com.aadavan.check.dsa;

import java.util.LinkedHashMap;
import java.util.Map;

public class FirstOccurrence {
    public static void main(String[] args) {
        System.out.println("\n\n\n");
        String word = "alpabhetspa";
        int maxFreq = 0;
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int i=0; i < word.length(); i++) {
            final char c = word.charAt(i);
            if (map.get(c) == null) {
                map.put(c, 1);
            }
            else {
                map.put(c, map.get(c) + 1);
            }
            if (maxFreq < map.get(c)) {
                maxFreq = map.get(c);
            }
        }
        System.out.println("map = " + map);
        for (Character key : map.keySet()) {
            final Integer integer = map.get(key);
            if (maxFreq == integer) {
                System.out.print("Max occurring alpahabet first is: " + key);
            }
        }
        System.out.println("\n\n\n");
    }
}

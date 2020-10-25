package com.aadavan.dsa.dp.contest2;

import java.util.*;

public class _04_PermutationAkhil {
    static List<String> output = new ArrayList<>();
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        permutation(s);
        for(int i=output.size()-1;i>=0;i--)
            System.out.print(output.get(i) + " ");
    }


    public static void permutation(String s) {
        trace(s, "");
    }

    public static void trace(String next, String combinations) {
        if (next.length() == 0)
            output.add(combinations);
        else {
            char c = next.charAt(0);
            next = next.substring(1);
            if (Character.isDigit(c))
                trace(next, combinations + c);
            else {
                trace(next, combinations + Character.toLowerCase(c));
                trace(next, combinations + Character.toUpperCase(c));
            }
        }
    }
}

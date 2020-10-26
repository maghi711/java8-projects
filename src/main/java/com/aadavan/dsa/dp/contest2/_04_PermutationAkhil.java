package com.aadavan.dsa.dp.contest2;

import java.util.*;

/*
    INPUT:  LLl6e
    OUTPUT: LLL6E LLL6e LLl6E LLl6e LlL6E LlL6e Lll6E Lll6e lLL6E lLL6e lLl6E lLl6e llL6E llL6e lll6E lll6e
 */
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

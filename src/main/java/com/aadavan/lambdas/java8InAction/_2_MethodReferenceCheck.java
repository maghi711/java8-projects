package com.aadavan.lambdas.java8InAction;

import java.util.Arrays;
import java.util.List;

public class _2_MethodReferenceCheck {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Mahesh J", "Aadavan", "Swetha", "Nexon", "Kalaiselvi", "Jyothi Ramalingam");
        System.out.println("before sort = " + names);
        //names.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        System.out.println("after sort = " + names);

        names.sort(String::compareToIgnoreCase);
        System.out.println("after sort = " + names);
    }
}

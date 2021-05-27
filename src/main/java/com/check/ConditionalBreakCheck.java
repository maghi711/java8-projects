package com.check;

import java.util.Arrays;
import java.util.List;

public class ConditionalBreakCheck {

    public static void main(String[] args) {
        final List<String> names = Arrays.asList("Swetha", "Mahesh", "Aadavan");
        System.out.println("Checking exceptional check");
        for (String name: names) {
            System.out.println("Working on " + name);
            if ("Swetha".equalsIgnoreCase(name)) {
                if (true) {
                    break;
                }
            }
            System.out.println("Finished for loop");
        }
        //names.stream().filter();
        //names.stream().anyMatch();
    }
}

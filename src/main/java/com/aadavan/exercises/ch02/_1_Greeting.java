package com.aadavan.exercises.ch02;

import java.util.Scanner;

public class _1_Greeting {
    private String name;

    /**
     * Reads name string from the console.
     * If it is not provided then a default name is assigned from the passed in parameter.
     * if parameter is also not passed then assign "Stranger" as the name.
     * @param defaultName
     * @return name
     */
    public String getName(String defaultName) {
        System.out.println("What is your name? ");
        Scanner scanner = new Scanner(System.in);
        this.name = scanner.nextLine();
        if (name == null || name.isEmpty()) {
            if (defaultName == null || defaultName.isEmpty()) {
                name = "Stranger";
            } else {
                name = defaultName;
            }
        }
        return name;
    }
}

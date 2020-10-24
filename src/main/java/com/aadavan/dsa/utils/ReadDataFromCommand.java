package com.aadavan.dsa.utils;

import java.util.Scanner;

/**
 * This class takes input and loops through until the entered input is 0.
 */
public class ReadDataFromCommand {
    static boolean logMessages = true;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        print("Enter the size you want to work with");
        long size = Long.parseLong(scan.nextLine());
        print("entered size is " + size);
        while (size != 0) {
            size--; // exit condition
            print("Execution iteration " + (size+1));
        }
    }

    static void print(String message) {
        if (logMessages) {
            System.out.println(message);
        }
    }
}

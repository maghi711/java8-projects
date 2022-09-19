package com.aadavan.dp.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AcceptInput {
    public static void main(String[] args) {
        readSecondInput();
    }

    static void readSecondInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Enter the number of time");
            int n = Integer.parseInt(br.readLine());
            System.out.println(n);
            while(n-- != 0) {
                System.out.println("Executing the cases -> " + n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void readInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the number");
        final int number;
        try {
            number = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("number = " + number);
        /*
        while (number != 0) {
            System.out.println("number = " + number);
        }
         */
    }
}

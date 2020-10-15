package com.aadavan.dsa.practice;

import java.util.Scanner;

public class EvenOdd {

    public static void main(String[] args) {
        System.out.println("Enter the number of data to input");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        System.out.println("Enter the values");
        while (i-- != 0) {
            int n = sc.nextInt();
            System.out.printf("%s\n", n%2 == 0? "TRUE":"FALSE");
        }
    }
}

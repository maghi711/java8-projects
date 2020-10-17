package com.aadavan.check.dsa.pre;

import java.util.Scanner;

public class Tribonacci {
    static Scanner scanner = new Scanner(System.in);
    public static void main (String[] args) throws java.lang.Exception
    {
        Tribonacci one = new Tribonacci();
        //System.out.println(one.tribonacci(0));
        System.out.println("Enter the num of test cases");
        int t = scanner.nextInt();
        while (t-- != 0) {
            System.out.println("Enter the tribonacci number");
            int n = scanner.nextInt();
            System.out.println(one.tribonacci(n));
        }
    }

    /**
     * nth tribonacci number is defined as
     * a(n) = a(n-1) + a(n-2) + a(n-3)
     * @param n
     * @return
     */
    public long tribonacci(int n) {
        long sum = 0;
        if (n == 0 || n == 1) {
            sum = 0;
        } else if(n == 2) {
            sum = 1;
        } else {
            sum = tribonacci(n-1) + tribonacci(n-2) + tribonacci(n-3);
        }
        return sum;
    }
}

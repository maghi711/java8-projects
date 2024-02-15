package com.aadavan.dsa.clj.module2.session10;

public class Prg5A {

    public static void main(String[] args) {
        int n = 10;
        if ((n & (n-1)) == 1) {
            print("2 Power");
        } else {
            print("Not a 2 Power");
        }
    }

    static <T> void print(T value) {
        System.out.println(value);
    }
}

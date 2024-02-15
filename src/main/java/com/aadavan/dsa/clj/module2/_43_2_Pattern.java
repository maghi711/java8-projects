package com.aadavan.dsa.clj.module2;

public class _43_2_Pattern {
    public static void main(String[] args) {
        int row = 10;
        int col = 1;
        for (int i = 0; i < row/2; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print("*");
            }
            col++;
            System.out.println();
        }
        col = col-2;
        for (int i = 0; i < row/2; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print("*");
            }
            col--;
            System.out.println();
        }
    }
}

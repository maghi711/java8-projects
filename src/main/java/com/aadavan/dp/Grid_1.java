package com.aadavan.dp;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Grid_1 {
}

/* Name of the class has to be "Main" only if the class is public. */
class Ideone
{
    public static void main (String[] args) throws java.lang.Exception
    {
        // your code goes here
        Scanner sc=new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        int[][] mat = new int[row][col];
        for(int i=0;i<row;i++)
            for(int j=0;j<col;j++)
                mat[i][j] = sc.nextInt();

        int c=mat[row-1][col-1];
        for(int j=col-2;j>=0;j--){
            mat[row-1][j]+=c;
            c=mat[row-1][j];
        }

        c=mat[row-1][col-1];
        for(int i=row-2;i>=0;i--){
            mat[i][col-1]+=c;
            c=mat[i][col-1];
        }

        for(int i=row-2;i>=0;i--)
            for(int j=col-2;j>=0;j--)
                mat[i][j] += Math.min(mat[i+1][j],mat[i][j+1]);

        printMatrix(mat);
        System.out.println(mat[0][0]);
    }

    static void printMatrix(int[][] result) {
        for (int i=0; i < result.length; i++) {
            System.out.println(Arrays.toString(result[i]));
        }
        System.out.println();
    }
}
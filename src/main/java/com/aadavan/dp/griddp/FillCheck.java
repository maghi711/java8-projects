package com.aadavan.dp.griddp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class FillCheck {
    public static void main(String[] args) {
        print();
        System.exit(1);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            final String inputTimes = br.readLine();
            final String[] s = inputTimes.split(" ");
            int r = Integer.parseInt(s[0]);
            int c = Integer.parseInt(s[1]);
            System.out.println("r = " + r);
            System.out.println("c = " + c);
            int[][] m = new int[r][c];
            while (r-- != 0) {
                final String col = br.readLine();
                final String[] s1 = col.split(" ");
                if (c == s1.length) {
                    for (int i = 0; i < s1.length; i++) {
                        m[r][i] = Integer.parseInt(s1[i]);
                    }
                }
            }
            print(m);
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void print(int[][] m) {
        for (int i = 0; i < m.length - 1; i++) {
            for (int j = 0; j < m[0].length - 1; j++) {
                System.out.println(m[i][j]);
            }
        }
    }

    static void print() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t--!=0){
            int m = sc.nextInt();
            int n = sc.nextInt();
            int a[][] = new int[m][n];
            int i, j;
            for(i=0;i<m;i++)
                for(j=0;j<n;j++)
                    a[i][j] = sc.nextInt();
            print(a);
        }
    }
}

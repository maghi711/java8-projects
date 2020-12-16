package com.aadavan.check.dsa.grid;

import java.util.*;

public class MaxSumTriangle {
    static int maxSumInTri(List< List<Integer> > mat){
        int r = mat.size();
        int dp[][] = new int[r+1][r+1];
        for (int i=0; i<r;i++) {
            for(int j=0;j<mat.get(i).size();j++) {
                if(r-1 == i) {
                    dp[i][j] = mat.get(i).get(j);
                }
            }
        }
        return 0;
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t--!=0){
            int r = sc.nextInt();
            //int c = sc.nextInt();

            // Create a List of null objects, where each object is List<Integer>
            List< List<Integer> > mat = new ArrayList<>();

            // It is important to init all one dimentional arrays
            for(int i=0;i<r;i++)
                mat.add(new ArrayList<>());

            for(int i=0;i<r;i++){
                for(int j=0;j<=i;j++){
                    int x = sc.nextInt();
                    mat.get(i).add(x);  // Add x in the ith List to the end
                }
            }

            System.out.println(maxSumInTri(mat));
        }
    }
}

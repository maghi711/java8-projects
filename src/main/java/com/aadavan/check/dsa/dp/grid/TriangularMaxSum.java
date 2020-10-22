package com.aadavan.check.dsa.dp.grid;
import java.util.*;

public class TriangularMaxSum {
    static List< List<Integer> > dp;
    static int maxSumInTri(List< List<Integer> > mat){
        int r = mat.size();
        dp = mat;
        System.out.println("size " + r);
        int sum = 0;
        for (int i=0;i<r;i++) {
            for (int j=0;j<mat.get(i).size()-1;j++) {
                dp.get(i).set(j, mat.get(i).get(j) + Math.max(dp.get(i+1).get(j), dp.get(i+1).get(j)));
                sum += dp.get(i).get(j);
            }
        }
        return sum;
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

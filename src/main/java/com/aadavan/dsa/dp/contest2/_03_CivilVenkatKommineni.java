package com.aadavan.dsa.dp.contest2;

import java.util.*;

public class _03_CivilVenkatKommineni {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner s=new Scanner(System.in);
        int n=s.nextInt();

        int a[][]=new int[n][3];
        int dp[][]=new int[n][3];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<3;j++)
            {
                a[i][j]=s.nextInt();
                dp[i][j]=0;
            }
        }


        for(int i=0;i<3;i++)
        {
            dp[n-1][i]=a[n-1][i];
        }

        for(int i=n-2;i>=0;i--)
        {
            for(int j=0;j<3;j++)
            {
                int k=0;int x=0;
                if(j==0)
                {
                    x=(int)Math.min(dp[i+1][1],dp[i+1][2]);
                }
                else if(j==1)
                {
                    x=(int)Math.min(dp[i+1][0],dp[i+1][2]);
                }
                else if(j==2)
                    x=(int)Math.min(dp[i+1][1],dp[i+1][0]);

                dp[i][j]=a[i][j]+x;
            }
        }

        int sum=0;
        sum=(int)Math.min(dp[0][0],(int)Math.min(dp[0][1],dp[0][2]));

        System.out.println(sum);
    }
}

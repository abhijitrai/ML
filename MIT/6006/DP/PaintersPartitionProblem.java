package DP;

import java.io.*;
import java.util.*;

// https://practice.geeksforgeeks.org/problems/the-painters-partition-problem1535/1/?page=1&curated[]=4&sortBy=submissions

public class PaintersPartitionProblem
{
    public static void main(String args[])throws IOException
    {
       /*
        Input
        n = 5
        k = 3
        arr[] = {5,10,30,20,15}
       *
       * */
    }
}


/*
* Problem : Find the min sub group sum , when partitioned by size k
* Sample : { 1,1,1,1,2,2,2,2 }
*
* Solution
*
* Sub Problem - Idea :  Prefix - take a prefix of both numbers in the Array and Number of Partitions
*               Sol [i, j ] = Min possible sum for 0 .... i numbers using 0...j partitions
*
*
* Relate    -   Idea : Sub problem expansion
*               If Sol[ i , y ] , Be the max possible sum for the prefix ( i.e. [ :i ] ) ending at 'i' with 'y' partition size
*               Then we can get the value of i + 1 by :
*                      Sol[ i+1 , y ] =         For all j in [ i+1 ..... 0 ]
*                                                   Min of {
*                                                                Max of (
*                                                                           Sum of [i+1 ... j ] ,
*                                                                           if( j-1 > 0 ) then Sol[ j -1 , y-1 ] else 0
*                                                                        )
*                                                          }
*
* Topology         - Increasing in i & j
* Base Case        - When k == 1
* O                - Sol[k,n-1]
* T                - KN^2
*
*
* */



 class Solution{
     
     
    static Integer max = Integer.MIN_VALUE;
    static long minTime(int[] arr,int n,int k){
        
        
        int[][] m = new int[k+1][n];
        int sum = 0, tmp = 0, max = 0 , min = Integer.MAX_VALUE;
        for( int i = 0 ;i< arr.length ;i++ ){
            sum += arr[i]; 
            m[1][i] =sum ; 
        }
        
        for( int y  = 2 ; y <= k ; y++  ) { 
            for( int i = 0 ;i< arr.length;i++  ) {
                min = Integer.MAX_VALUE ;
                sum = 0 ; 
                for( int j = i ; j >= 0 ; j-- ) { 
                    max = 0 ;
                    tmp = 0 ;
                    sum += arr[j] ;
                    if( (j -1) >=0 ){
                        // System.out.println("Getting tmp  before");
                        tmp = m[y-1][j-1]; 
                    }
                    max = Math.max(sum,tmp);
                    // System.out.println("Y =" +y + " i=" +i + " j="+j + " arr[j]="+ arr[j] + " tmp = "+ tmp+ " sum =" +sum+ " min = " + min + " max = " + max);
                    min = Math.min( min,max);   
                }
                m[y][i] = min ;
                
            }
            // for( int [] row : m )
            //         System.out.println(Arrays.toString(row)        );
        }
        
        // for( int [] row : m )
        //     System.out.println(Arrays.toString(row)        );
        return m[k][arr.length-1];        
        
        
    }
    
    
    
    
        
        
    
}




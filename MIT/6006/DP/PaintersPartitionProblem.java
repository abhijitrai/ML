// { Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

// https://practice.geeksforgeeks.org/problems/the-painters-partition-problem1535/1/?page=1&curated[]=4&sortBy=submissions

public class PaintersPartitionProblem
{
    public static void main(String args[])throws IOException
    {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while(t-- > 0)
        {
            String input_line1[] = read.readLine().trim().split("\\s+");
            int k = Integer.parseInt(input_line1[0]);
            int n = Integer.parseInt(input_line1[1]);
            String input_line[] = read.readLine().trim().split("\\s+");
            int arr[]= new int[n];
            for(int i = 0; i < n; i++)
                arr[i] = Integer.parseInt(input_line[i]);
            
            Solution ob = new Solution();
            System.out.println(ob.minTime(arr,n,k));
        }
    }
}

// } Driver Code Ends


//User function Template for Java

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




package dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Coefficient {


    int[][] g ;
    /*
    *
    ( k,n ) == Sum ( ( n-1 , k-1 ) + ( n -1 , k) )
    *
    *
    *
    *       0,1 ,2, 3, 4, 5, 6, 7, 8, 9 ... n
    *    0
    *    1
    *    2
    *    3
    *    4
         5
    *    .
    *    .
    *    K
    */

    public static void main(String... args){

        Coefficient c = new Coefficient();
        int n =8;
        int k =5 ;
        System.out.println("Coeff " + n + " " + k + " = " + c.binCoeff(k,n) ) ;
        for( int i =0 ;i < n +1 ;i ++ ){
            System.out.println (Arrays.toString(c.g[i]));
        }

    }

    int binCoeff( int k , int n ){

        g = new int[n+1][n+1];

        for( int i =0 ; i< n+1 ; i++   ){
            g[i][0] = 1;
        }

        for( int i = 0 ; i< n+1 ; i++) {
            for(int j = 0 ; j< n+1 ;j++) {
                if( i == j )
                    g[i][j] = 1;
            }
        }

        for( int i = 2 ; i< n+1 ; i++) {
            for (int j = 1; j < i; j++) {
                g[i][j]  = g[i-1][j-1] + g[ i-1 ][j];
            }
        }
        return g[n][k];


    }


}

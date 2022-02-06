package dynamicFirstCrack;

import java.util.Arrays;

public class PascalNumber {


        public static void main(String... args ) {
            PascalNumber pn = new PascalNumber();
            pn.compute(5);
            System.out.println ("Number -> " + pn.solutions[4][2] ) ;
        }

    int[][] solutions;

    void compute(int n) {
        solutions = new int[n][n];
        for (int i = 0; i < n; i++) {
            solutions[i][0] = 1;
            if( i < n){
                solutions[i][i] = 1;
            }
        }


        for (int i = 2; i < n ;  i++ ){
            for (int j = 1;  j < n  ; j++) {
                System.out.println(" i : " + i + " \t j : "  + j );
                for( int q = 0 ; q<solutions.length ; q++ ){
                    System.out.println(Arrays.toString(solutions[q]));
                }
                solutions[i][j] = solutions[i - 1][j] + solutions[i - 1][j - 1];
            }
        }


    }

}


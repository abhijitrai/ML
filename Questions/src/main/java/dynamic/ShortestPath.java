package dynamic;

import java.util.Arrays;
import java.util.Random;

public class ShortestPath {



    /*
        A --- B
     */

    public static void main(String... args ) {

        int bound = 100 ;
        int r  = 200 ;
        Integer[] m = new Integer[10];
        int [] g = new int[10] ;
        for( int i = 1 ; i <= 10 ; i++ ) {
            m[i-1] = new Random().nextInt(100 * i ) ;
        }
        Arrays.sort(m);
//        System.out.println(Arrays.toString(m));

        /*
        *
        *  Min ( number of stops ) between A - B
        *
        *  G( j ) =   Min of stops needed to reach  m( j )  , where
        *
        *  1 )  j < i  &  ( m(i) - m(j) ) < R
        *
        * */

        int imax = m.length ;
        g[0] = 1;
        int sum = 0 ;

        System.out.println("Distances -> " + Arrays.toString(m));
        System.out.println("Min Hops Array -> " + Arrays.toString(g));
        System.out.println("Milage -> " + r);
        System.out.println("Min hops  -> "+ g[m.length -1 ]);


    }




}

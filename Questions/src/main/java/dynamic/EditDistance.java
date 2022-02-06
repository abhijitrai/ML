package dynamic;

import java.util.Arrays;

public class EditDistance {

    /**
     * s -> abc -> 0,1,2,3, ... i
     * t -> xyz -> 0,1,2,3, ... j
     * <p>
     * M ( 0.. i, 0... j ) ->
     * M ( 0...i-1 , 0....j-1  ) + 1 ( match )
     * M ( 0...i-1 , 0....j-1  ) + 1 ( swap )
     * M ( 0...i-1 , 0....j  ) + 1 ( delete  )
     * M ( 0...i , 0....j-1  ) + 1 ( insert  )
     */

    public static void main(String... args) {
        new EditDistance().minDist("thou-shalt", "you-should");
//        new EditDistance().minDist("thou", "you");
//        new EditDistance().minDist("got", "hot");
    }

    int minDist(String convert, String target) {


        String[] x1 = convert.split("");//x
        String[] y1 = target.split("");//y
        String[] x = new String[x1.length +1 ];
        String[] y = new String[y1.length+1];
        for (int  i = 0;  i < x1.length +1 ;  i++) {
            if( i ==0 ) {
                x[i] = "";
            }else {
                x[i] = x1[i - 1];
            }
        }
        for (int  i = 0;  i < y1.length +1 ;  i++) {
            if( i ==0 ) {
                y[i] ="";
            }else {
                y[i] = y1[i - 1];
            }
        }


        int[][] m = new int[x.length][y.length];

        for (int i = 0; i < y.length; i++) {
            m[0][i] = i;
        }
        for (int i = 0; i < x.length; i++) {
            m[i][0] = i;
        }


//        printCost(m);

        for (int i = 1; i < x.length; i++) {
            for (int j = 1; j < y.length; j++) {
                int min = 0;
                int cost1;
                if (x[i] .equals(y[j])) {
                    cost1 = m[i - 1][j - 1];
                } else {
                    cost1 = m[i - 1][j - 1] + 1;
                }
                int cost2 = m[i - 1][j] + 1;
                int cost3 = m[i][j - 1] + 1;

                if (cost1 > cost2) {
                    if (cost3 < cost2) {
                        min = cost2;
                    } else {
                        min = cost3;
                    }
                } else {
                    if (cost3 < cost1) {
                        min = cost3;
                    } else {
                        min = cost1;
                    }
                }
                System.out.println(" From  ( " +  combine ( x,i) + "  To ( " + combine(y,j) + " ) " +
                        "Cost[ : " +cost1 + " : " + cost2 + " : " + cost3  + "] ->  " + min );
                m[i][j] = min;

            }
        }

        printCost(m);
        System.out.println(" Min -> " + m[x.length - 1][y.length - 1]);

        return m[x.length - 1][y.length - 1];


    }

    String combine ( String[]arr , int  idx ){
        StringBuilder sb = new StringBuilder();
            for(int i = 0 ;i <= idx ; i++ ){
               sb.append(arr[i]);
            }
        return sb.toString();
    }

    void printCost(int[][] cost) {
        for (int i = 0; i < cost.length; i++)
            System.out.println(Arrays.toString(cost[i]));

    }


}


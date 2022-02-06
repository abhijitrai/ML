package dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Feb {


    List<Integer> g = new ArrayList<>();

    public static void main(String... args){

        Feb f= new Feb() ;
        int x = 25;
        System.out.println(" Feb of "+ x + " = " + f.feb(x) );

    }


    int feb ( int x ){
        g.add(1);
        g.add(1);
        for ( int i =2 ; i< x + 3  ;i ++ ) {
            g.add(g.get(i- 1) + g.get(i - 2));
            System.out.println(" G - > " + g);
        }
        return g.get(x);

    }
}

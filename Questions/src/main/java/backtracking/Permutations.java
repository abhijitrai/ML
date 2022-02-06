package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations {


    int [] unAvailable ;
    List<List<Integer> > solutions = new ArrayList<>();
    /**
     *
     *  1,2,3,4
     *
     *  1 (4) , 2(3) , 4(2) ,1
     *
     */

    public static void main(String[] args) {
        Permutations obj = new Permutations();
//        int[] inp = new int[]{21,34,15,32};
        int[] inp = new int[]{1,2,3};
        obj.permute( inp , new ArrayList<Integer>());
        int count = 0 ;
        for(int i = 0 ; i< obj.solutions.size();i++){
            System.out.println(obj.solutions.get(i));
            count ++ ;
        }
        System.out.println(" Total -> " + count ) ;
    }

    void permute( int arr[] , List< Integer>chosen ){

        if( chosen.size() == arr.length)
        {
            solutions.add(chosen);
            return;
        }

        for( int i = 0 ; i<arr.length ; i++  ){
            boolean available = true;
            for( int j = 0 ; j<  chosen.size() ; j ++ ){
                    if( i == chosen.get(j))
                        available = false;
            }
            if( available){
//                System.out.println("Choosing -> " + i);
                List nextChosen = new ArrayList(chosen);
                nextChosen.add(i);
                permute(arr,nextChosen);
            }
        }
    }







}

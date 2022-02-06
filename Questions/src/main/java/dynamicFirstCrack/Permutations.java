package dynamicFirstCrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Permutations {


    public static void main(String... args) {

//        int []arr = new int[]{1};
//        int []arr = new int[]{1, 2};
        int []arr = new int[]{1, 2,3};
//        int []arr = new int[]{1, 2, 3, 4, 5, 6};
        Permutations p = new Permutations();
//        p.permute();
        p.permute(arr);
        System.out.println(p.solutions.get(arr.length -1));

    }

    Map<Integer, List<List<Integer>>> solutions = new HashMap<>();

    void permute(int[] arr){

        List<List<Integer>> l =  new ArrayList<>();
        List<Integer> l2 = new ArrayList();
        l2.add(arr[0]);
        l.add(l2);
        solutions.put( 0 , l);
        for( int i = 1;i< arr.length; i++ ){
            permutations(arr,i);
        }

    }

    void permutations(int[] arr, int idx) {

        List<List<Integer>> l = solutions.get(idx - 1);
        List<List<Integer>> l2 = new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {
            l2.addAll(addOne(l.get(i), arr[idx]));
        }

        solutions.put(idx, l2);
    }


    List<List<Integer>> addOne(List<Integer> l, int x) {
        List<List<Integer>> l1 = new ArrayList<>();
        for (int i = 0; i < l.size() +1 ; i++) {
            List<Integer> l2 = new ArrayList<>();
            boolean bump = false;
            for (int j = 0; j < l.size()+1 ; j++) {
                if (i == j) {
                    l2.add(x);
                    bump = true;
                    continue;
                }
                if (bump) {
                    l2.add(l.get(j - 1));
                } else {
                    l2.add(l.get(j));
                }
            }


            l1.add(l2);
        }
//        System.out.println(" Number : " + x + " Added to List : " + l + " New List " + l1);
        return l1;

    }


}

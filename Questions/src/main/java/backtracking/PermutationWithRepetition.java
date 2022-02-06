package backtracking;

import java.util.*;

public class PermutationWithRepetition {

    public static void main(String... args) {

        PermutationWithRepetition per = new PermutationWithRepetition();
        per.permute(new int[] {1,1,2,2 });
//        per.permute(new int[] {1,2,1 });
//        per.permute(new int[] {1,2,3 });
        System.out.println("Count " + per.solutions.size() + " \n Solutions " + per.solutions);
    }

    void permute(int[] arr) {
        this.length = arr.length;
        Map<Integer, Integer> cM = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (cM.keySet().contains(arr[i])) {
                int count = cM.get(arr[i]);
                cM.put(arr[i], count + 1);

            } else {
                cM.put(arr[i], 1);
            }
        }
        calculatePermutations(null  , cM);
    }

    List<List<Integer>> solutions = new ArrayList() ;
    int length ;

    void calculatePermutations(  List<Integer> list , Map<Integer,Integer> choices)
    {
        if(list!=null && list.size() == length ){
            solutions.add(list);
            return;
        }
        Iterator<Integer> it = choices.keySet().iterator() ;
        while( it.hasNext()){
            int key = it.next();
            System.out.println("Considering Key : " + key );
            if( choices.get(key) > 0 ){
               // Using choice
                Map<Integer,Integer> newMap = new HashMap(choices);
                int count = newMap.get(key) ;
                count = count-1 ;
                newMap.put(key,count);
                List<Integer>  newList;

                if( list!= null ) {
                     newList = new ArrayList(list);
                }else{
                    newList =  new ArrayList<>();
                }
                newList.add(key);
                System.out.println("New List : " + newList + " New Map " + newMap  );
                calculatePermutations(newList , newMap);

            }else{
                System.out.println("No peices avaiable  : " + key );
            }
        }


    }



}

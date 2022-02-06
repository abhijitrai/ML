package dynamicFirstCrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllSubsets {

    public static void main(String... args) {
        AllSubsets p = new AllSubsets();
        int[] arr = new int[]{1, 2, 3, 4} ;
        p.allSubsetsDynamic(arr);
        System.out.println(p.solutions.get(arr.length -1));

    }

    Map<Integer, List<List<Integer>>> solutions = new HashMap();

    void allSubsetsDynamic(int[] arr) {
        List<List<Integer>> initS = new ArrayList();
        initS.add(new ArrayList<>());
        List l2 = new ArrayList<>();
        l2.add(arr[0]);
        initS.add(l2);
        solutions.put(0, initS);
        for (int i = 1; i < arr.length; i++) {
            allSubsets(arr, i);
        }
    }

    List<List<Integer>> allSubsets(int[] arr, int idx) {

        List<List<Integer>> subsets = solutions.get(idx - 1);
//        System.out.println(" ID : " +idx +":" + subsets ) ;
        List<List<Integer>> additionalSubsets = copy(subsets);
        for (int i = 0; i < additionalSubsets.size(); i++) {
            List<Integer> l = additionalSubsets.get(i);
            l.add(arr[idx]);
        }
        additionalSubsets.addAll(subsets);
        solutions.put(idx, additionalSubsets);
        return additionalSubsets;

    }

    List<List<Integer>> copy( List<List<Integer>> l1 ){

        List<List<Integer>> l2 = new ArrayList();
        for( int i= 0 ;  i< l1.size();i++){
            List temp =  new ArrayList();
            if( l1.get(i).size ()> 0){
                for( int j=0 ;j< l1.get(i).size();j++){
                    Integer it = new Integer(l1.get(i).get(j));
                    temp.add(it);
                }
            }
            l2.add(temp);
        }
        return l2;


    }

}

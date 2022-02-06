package dynamicFirstCrack;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermutationsWithRepetition {

    public static void main(String... args) {
//        int[] arr = {4, 2, 4};
        int[] arr = {1,1,2,1};
        PermutationsWithRepetition p = new PermutationsWithRepetition();
        p.permute(arr);
        System.out.println(" Count : " + p.solutions.get(arr.length -1).size() + "  Solutions : " + p.solutions.get(arr.length -1));
    }

    Map<Integer, List<List<Integer>>> solutions = new HashMap<>();

    void permute(int[] arr) {
        List<List<Integer>> l = new ArrayList();
        List<Integer> l1 = new ArrayList();
        l1.add(arr[0]);
        l.add(l1);
        solutions.put(0, l);

        for (int i = 1; i < arr.length; i++) {
            permute(arr, i);
        }

    }

    void permute(int[] arr, int idx) {
//        System.out.println(" ID -> " + idx);
        List<List<Integer>> l = solutions.get(idx - 1);
//        System.out.println("Last IDx " + (idx-1) +" Current id "+ idx + " Current Element " + arr[idx] +" Existing Soln " + l );
        List<List<Integer>> l2 = new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {
            List<List<Integer>> l3 = addOneWithRepetitions(arr,l.get(i), arr[idx]);
            for( int j = 0 ;j < l3.size();j++){
                if( !l2.contains(l3.get(j))){
                    l2.add(l3.get(j));
                }
            }
        }
        solutions.put(idx,l2);
    }

    List<List<Integer>> addOneWithRepetitions(int [] arr , List<Integer> l, int x) {
        List<List<Integer>> l3 = new ArrayList();
        List<Integer> l4 = new ArrayList();
        for (int i = 0; i < l.size(); i++) {
            if (x == l.get(i) ) {
//                System.out.println("Adding to skip list " + i ) ;
                l4.add(i);
            }
        }
        for (int i = 0; i < l.size() + 1; i++) {
            if (l4.contains(i)) {
                continue;
            }
            List<Integer> l2 = new ArrayList<>();
            for (int j = 0; j < l.size(); j++) {
                if (i == j) {
                    l2.add(x);
                }
                l2.add(l.get(j));
            }
            if( i == (l.size()))
                l2.add(x);
            l3.add(l2);
        }
        return  l3;
    }


}

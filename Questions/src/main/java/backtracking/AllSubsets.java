package backtracking;

import java.util.ArrayList;
import java.util.List;

public class AllSubsets {

    public static void main(String[] args) {
        int[] inp = new int[]{21,34,15,32};
//        new int[]{23, 42, 53, 24, 15,56}
        List<List<Integer>> subsets = new AllSubsets().findSubsets(inp, inp.length -1 );
        for (int i = 0; i < subsets.size(); i++) {
            List<Integer> l = subsets.get(i);
            System.out.println(" " + l);
        }
        System.out.println("Count : " + subsets.size());
    }



    public List<List<Integer>> findSubsets(int[] arr, int idx) {
        if( idx<0 )
            return new ArrayList() ;
        if (idx ==0 ) {
            List lists = new ArrayList();
            List l1 = new ArrayList<>();
            l1.add(arr[0]);
            List l2 = new ArrayList<>();
            lists.add(l1);
            lists.add(l2);
            return lists;
        }
        List<List<Integer>> childList = findSubsets(arr, idx - 1);
        List<List<Integer>> additions = copy(childList, arr[idx]);
        childList.addAll(additions);
        return childList;
    }

    List<List<Integer>> copy(List<List<Integer>> l, Integer x) {
        List<List<Integer>> newList = new ArrayList();
        for (int i = 0; i < l.size(); i++) {
            List<Integer> val = l.get(i);
            List<Integer> nl = new ArrayList<>();
            for (int j = 0; j < val.size(); j++) {
                nl.add(new Integer(val.get(j)));
            }
            nl.add(x);
            newList.add(nl);
        }
        return newList;
    }
}


package sort;

import java.util.*;

public class MergeSort {

       public static void main(String[] args) {

        Integer [] arr = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};

        Integer[] arr2 = {0,1,3,3,0};

        //  Integer[] list = sort(new Integer[]{1,3,1,0});
        Integer [] list = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
        list = sort( list);
        for (int i = 0; i < list.length; i++) {
            System.out.print(" " + list[i]);
        }
    }

    static Integer[] sort(Integer[] arr){
        if(arr.length ==1 )
            return arr;

        long center = arr.length / 2 ;
        List<Integer> leftList = new ArrayList<Integer>();
        List<Integer> rightList = new ArrayList<Integer>();
        for (int i = 0; i < arr.length; i++) {
            if(i<center)
                leftList.add(arr[i]);
            else
                rightList.add(arr[i]);
        }
        Integer[] sortedLeft = sort(leftList.toArray(new Integer[]{}));
        Integer[] sortedRight = sort(rightList.toArray(new Integer[]{}));
        return merge(sortedLeft, sortedRight);
    }

    static Integer[] merge(Integer arr1[] , Integer arr2[] ){
        List<Integer> sortedList = new ArrayList<Integer>();
        int idx1 = 0 ;
        int idx2 = 0 ;

        for (int i = 0; i < arr1.length + arr2.length ; i++) {

            if(idx1 >= arr1.length){
                while(idx2 < arr2.length)
                {
                    sortedList.add(arr2[idx2]);
                    idx2++;
                };
                break;
            }
            if(idx2 >= arr2.length){
                while(idx1 < arr1.length)
                {
                    sortedList.add(arr1[idx1]);
                    idx1++;
                };
                break;
            }

            if(arr1[idx1] >= arr2[idx2])
            {
                sortedList.add(arr2[idx2]);
                idx2++;
            }else{
                sortedList.add(arr1[idx1]);
                idx1++;
            }
        }

        return sortedList.toArray(new Integer[]{});
    }
}


package com.example.demo.recursion;

import java.util.Arrays;

import static java.lang.String.valueOf;

public class Recursion {

    public static void main(String[] args) {
//        printRecursive();
//        printRecursive2();
//        searchRecursive();
          sortRecursive();
    }

    private static void searchRecursive() {
        int[][] array = {{1}, {1, 2}, {1, 2, 3}, {1, 2, 3, 4}, {1, 2, 3, 4, 5}};
        int item = 4 ;
        for (int i = 0; i < array.length; i++) {
            System.out.println("Input -> " + Arrays.toString(array[i]) + " Start -> " + 0 + " End -> " + valueOf(array[i].length - 1));
            SearchRecursive.binarySearch(array[i], 0, array[i].length - 1 , item );
            System.out.println("*******************************************");
        }
    }

    private static void printRecursive() {
        int[][] array = {{1}, {1, 2}, {1, 2, 3}, {1, 2, 3, 4}, {1, 2, 3, 4, 5}};
        for (int i = 0; i < array.length; i++) {
            System.out.println("Input -> " + Arrays.toString(array[i]) + " Start -> " + 0 + " End -> " + valueOf(array[i].length - 1));
            PrintRecursive.print(array[i], 0, array[i].length - 1);
            System.out.println("*******************************************");
        }
    }
    private static void printRecursive2() {
        int[][] array = {{1}, {1, 2}, {1, 2, 3}, {1, 2, 3, 4}, {1, 2, 3, 4, 5}};
        for (int i = 0; i < array.length; i++) {
            System.out.println("Input -> " + Arrays.toString(array[i]) + " Start -> " + 0 + " End -> " + valueOf(array[i].length - 1));
            PrintRecursive.print2(array[i], 0, array[i].length - 1);
            System.out.println("*******************************************");
        }
    }

    private static void sortRecursive() {
        int[][] array = {{1}, {1, 2}, {3, 2, 1}, {4, 2, 1, 4}, {5, 3, 1, 4, 2}};
        for (int i = 0; i < array.length; i++) {
            System.out.println("Input -> " + Arrays.toString(array[i]) + " Start -> " + 0 + " End -> " + valueOf(array[i].length - 1));
            System.out.println("Result -> " + Arrays.toString(MergeSort.sort(array[i], 0, array[i].length - 1) )) ;
            System.out.println("*******************************************");
        }
    }
}

class PrintRecursive {

    public static int[] print(int[] array, int start, int end) {
        if (end < start)
            return array;
        int mid = (end + start) / 2;
        System.out.println("Processed ( Mid ) ->  " + mid + " Start ( " + start + ") End (" + end + ")");
        System.out.println("Recurse Start :  " + start + " End : " + valueOf(mid - 1));
        System.out.println("Recurse Start :  " + valueOf(mid + 1 ) + " End : " + valueOf(end));
        print(array, start, mid - 1);
        print(array, mid + 1, end);
        return null;
    }

    public static int[] print2(int[] array, int start, int end) {
        if (end < start)
            return array;
        if (end == start) {
            System.out.println("Processed  Start ( " + start + ") End (" + end + ")");
            return array;
        }

        int mid = (end + start) / 2;
        System.out.println("Mid -> " + mid);
        System.out.println("Recurse Start :  " + start + " End : " + valueOf(mid ));
        System.out.println("Recurse Start :  " + valueOf(mid +1 ) + " End : " + valueOf(end));
        print2(array, start, mid);
        print2(array, mid +1 , end);
        return null;
    }


}

class SearchRecursive {

    public static Integer binarySearch(int[] array, int startIdx, int endIdx, int item) {
        if (endIdx < startIdx)
            return null;
        int mid = (startIdx + endIdx) / 2;
        if (array[mid] == item) {
            System.out.println("Value found at Mid -> " + mid);
            return new Integer(mid);
        }
        if (item < array[mid]) {
            System.out.println("Recurse ( Mid ) ->  " + mid + " Start ( " + startIdx+ ") End (" + valueOf(mid - 1)  + ")");
            binarySearch(array , startIdx , mid - 1  ,item );
        } else {
            System.out.println("Recurse ( Mid ) ->  " + mid + " Start ( " + valueOf(mid +1) + ") End (" + endIdx + ")");
            binarySearch(array , mid +1 , endIdx  ,item );
        }
        return null;
    }

}

class MergeSort{
    public static int[] sort(int[] array, int start, int end) {
        if(end < start){
            return null;
        }
        if(end == start ){
            System.out.println("Processed  Start ( " + start + ") End (" + end + ")");
            return new int[]{array[end]};
        }

        int mid = ( start + end )/ 2 ;
        System.out.println("Mid -> " + mid);
        System.out.println("Recurse Start :  " + start + " End : " + valueOf(mid ));
        System.out.println("Recurse Start :  " + valueOf(mid +1 ) + " End : " + valueOf(end));
        int[] temp1 = sort(  array ,  start  ,  mid  );
        int[] temp2 = sort(  array ,  mid +1 ,  end );
        return merge(temp1,temp2) ;

    }

    private static  int[] merge(int[] temp1, int[] temp2) {

      int idxTemp1 = 0 , idxTemp2 = 0 ;
      int[] combined = new int[temp1.length + temp2.length];
        for (int i = 0; i < temp1.length + temp2.length; i++) {
            if( idxTemp1 <= temp1.length-1 && idxTemp2 <= temp2.length-1 ){
                if(temp1[idxTemp1] < temp2[idxTemp2]){
                    combined[i] = temp1[idxTemp1];
                    idxTemp1++;
                }else {
                    combined[i] = temp2[idxTemp2];
                    idxTemp2++;
                }
            }else if( idxTemp1 <= temp1.length -1 ){
                combined[i] = temp1[idxTemp1];
                idxTemp1++;
            }else if (idxTemp2 <= temp2.length -1 ){
                combined[i] = temp2[idxTemp2];
                idxTemp2++;
            }
        }System.out.println("Merge called with : " + Arrays.toString(temp1)+ "\t" + Arrays.toString(temp2) + "\t Combined -> " + Arrays.toString(combined));
        return combined;

    }


}



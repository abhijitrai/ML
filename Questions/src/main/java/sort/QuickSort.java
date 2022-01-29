package sort;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    public static void main(String[] args) {
        int[] array = {1,2,3,4,62,4,36,23,12,643,23423,35,5};
//        int[] array = {1,4,2,3};
        System.out.println("Size : " + array.length + " Before -> " + Arrays.toString(array));
        sort(array, 0,array.length-1 );
        System.out.println(" Sorted -> " + Arrays.toString(array));
    }


    static void sort(int[]array , int startIdx, int endIdx){
        if(endIdx<startIdx || endIdx == startIdx)
            return ;
        int pv = new Random().nextInt(endIdx - startIdx) + startIdx;
        swap(array,pv,endIdx);
        int pivot = array[endIdx];
        int left = startIdx;
        int right = endIdx -1 ;
//        System.out.println("Pass:S left : " + left+ " pv " + pv   + " right "  + right + "\t\tArray : "+ Arrays.toString(array));
        while( left < right ){
            while ( array[left] < pivot){
                left++ ;
            }
            while( array[right] > pivot && left < right ){
                right-- ;
            }
            if(left < right ){
                System.out.println("Swap "+ left + " : "+ right);
                swap(array,left ,right );
            }

        }
//        System.out.println("Pass:E left : "  + left + " pv " + pv   + " right "  + right  + "\t\tArray : "+ Arrays.toString(array));
        if(left == (endIdx -1) ){
            if( array[left] > array[endIdx]){
                swap(array,left,endIdx);
            }
            sort(array, startIdx,endIdx-1);
        }else{
              swap(array,left,endIdx);
              sort(array, startIdx,left-1);
              sort(array, left +1,endIdx);
        }



    }

    public static void swap(int[] array , int x, int y){
        int temp = array[x];
        array[x]=array[y];
        array[y] =temp ;
    }

}

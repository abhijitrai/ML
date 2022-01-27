package recurse;

import java.util.Arrays;
import java.util.Random;

import static java.lang.String.valueOf;

public class Recursion2 {

    public static void main(String[] args) {
//        printPairs();
        QuickSort.quickSortDemo();

    }


}

class QuickSort {

    static void quickSortDemo() {
        int[][] array = {{1}, {1, 2}, {1, 1, 2}, {4, 24,54, 2, 1}, {1, 3,21, 3, 44, 5}, {12, 123, 412, 534, -12, 234, 634, 34}};
        for (int i = 0; i < array.length; i++) {
            System.out.println("Input -> " + Arrays.toString(array[i]) + " Start -> " + 0 + " End -> " + valueOf(array[i].length - 1));
            QuickSort.sort(array[i], 0, array[i].length - 1);
            System.out.println("Sorted -> " + Arrays.toString(array[i]));
            System.out.println("*******************************************");
        }
    }

    static void sort(int arr[], int start, int end) {
        if (end < start)
            return;
        if (end == start)
            return;
        Random random = new Random();
        int pv = random.nextInt(end - start) + start;
        int fwdIdx = start;
        int rvIdx = end - 1;
        swap(arr,pv,end);
//        System.out.println("Target Array -> " + Arrays.toString(Arrays.copyOfRange(arr,start,end+1)));
//        System.out.println("Processing with :  fwd -> "+ fwdIdx + " pv -> " + pv + " rev -> " + rvIdx );
        while (fwdIdx < rvIdx) {
            while (arr[fwdIdx] < arr[end] && fwdIdx < rvIdx) {
                fwdIdx++;
            }
            while (arr[rvIdx] > arr[end] && fwdIdx < rvIdx) {
                rvIdx--;
            }
            if (fwdIdx < rvIdx) {
                swap(arr,fwdIdx,rvIdx);
//                System.out.println(" Swap  " + arr[rvIdx] + " :  " + arr[fwdIdx]  );
            }
        }
//        System.out.println("Post Iteration Array -> " + Arrays.toString(arr) + " Fwd ->  " + fwdIdx + " Rev -> " + rvIdx);
        if (fwdIdx == end - 1) {
            if (arr[fwdIdx] > arr[end]) {
                swap(arr,fwdIdx,end);
            }
            sort(arr, start, end - 1);
        } else {
            swap(arr,fwdIdx,end);
            sort(arr, start, fwdIdx - 1);
            sort(arr, fwdIdx + 1, end);
        }


    }

    static private void swap(int[] arr ,int id1, int id2){
        int temp = arr[id1];
        arr[id1] = arr[id2];
        arr[id2] = temp;
    }


}


class PrintRecursivePairs {

    public static void printPairs() {
        int[][] arr = {{1, 2}, {3, 3}, {2, 4}, {4, 5}, {5, 6}, {8, 5}, {5, 6}};
        int[][] result = recursiveSort(arr, 0, arr.length - 1, false);
        System.out.println("Result -> \n");
        print(result);
        result = recursiveSort(arr, 0, arr.length - 1, true);
        System.out.println("\nResult -> \n");
        print(result);
    }

    static void print(int[][] result) {
        for (int i = 0; i < result.length; i++) {
            System.out.print(Arrays.toString(result[i]));
        }
    }

    static int[][] recursiveSort(int[][] elements, int startIdx, int endIdx, boolean invert) {
        if (endIdx < startIdx)
            return null;
        if (endIdx == startIdx)
            return new int[][]{{elements[startIdx][0], elements[startIdx][1]}};

        int mid = (startIdx + endIdx) / 2;
        int[][] array1 = recursiveSort(elements, startIdx, mid, invert);
        int[][] array2 = recursiveSort(elements, mid + 1, endIdx, invert);
        int[][] array3 = combine(array1, array2, invert);
//        print(array1); System.out.print(" \t "); print(array2);System.out.print(" \t "); print(array3);
//        System.out.print(" \n ");
        return array3;
    }

    static private int[][] combine(int[][] array1, int[][] array2, boolean invert) {
        if (array1 == null && array2 == null) {
            return null;
        } else if (array1 == null) {
            return array2;
        } else if (array2 == null) {
            return array1;
        } else {
            int idx1 = 0, idx2 = 0;
            int[][] combined = new int[array1.length + array2.length][2];
            for (int i = 0; i < array1.length + array2.length; i++) {
                if (idx1 < array1.length && idx2 < array2.length) {
                    if (!invert) {
                        if (array1[idx1][0] < array2[idx2][0]) {
                            combined[i][0] = array1[idx1][0];
                            combined[i][1] = array1[idx1][1];
                            idx1++;
                        } else {
                            combined[i][0] = array2[idx2][0];
                            combined[i][1] = array2[idx2][1];
                            idx2++;
                        }
                    } else {
                        if (array1[idx1][1] < array2[idx2][1]) {
                            combined[i][0] = array1[idx1][0];
                            combined[i][1] = array1[idx1][1];
                            idx1++;
                        } else {
                            combined[i][0] = array2[idx2][0];
                            combined[i][1] = array2[idx2][1];
                            idx2++;
                        }
                    }

                } else if (idx1 < array1.length) {
                    combined[i][0] = array1[idx1][0];
                    combined[i][1] = array1[idx1][1];
                    idx1++;


                } else if (idx2 < array2.length) {
                    combined[i][0] = array2[idx2][0];
                    combined[i][1] = array2[idx2][1];
                    idx2++;

                }
            }

            return combined;

        }

    }


}

package stablesort;

import java.util.*;

public class SegmentTree {

    static int max(int[] arr ,int idx1,int idx2){
        int max = Integer.MIN_VALUE;
        while(idx1<idx2){
//            System.out.println(idx1 + " - "+ idx2);
//            If Idx1 is left its ok , else compare it with one level up i.e. idx1++/2
//            If Idx2 is right its ok , else compare it with one level up i.e. idx2--/2
            if(idx1%2 != 0) {
                int tmp = arr[idx1];
                idx1++;
                tmp = Math.max(tmp,arr[idx1]);
                max = Math.max(max,tmp);
            }else{
                max = Math.max(max,arr[idx1]);
            }
            if(idx1%2 == 0) {
                int tmp = arr[idx2];
                idx2--;
                tmp = Math.max(tmp,arr[idx2]);
                max = Math.max(max,tmp);
            }else{
                max = Math.max(max,arr[idx2]);
            }
            idx1 = idx1/2;
            idx2 = idx2/2;
        }
        return max;
    }

    static void update (int[]arr , int idx , int newVal ){
        arr[idx] = newVal;
        while( idx>0){
            idx = idx/2 ;
            arr[idx] = Math.max(arr[2*idx],arr[2*idx +1] );
        }
    }

    static int  populate(int[] arr, int st, int end){
        int newSt = -1;
        for(int i=st;i<=end;i+=2){
            if(i==end){
                arr[i/2] =  arr[end];
            }else{
                arr[i/2 ]= Math.max(arr[i],arr[i+1]);
            }
        }
        newSt = st/2;
//        System.out.println(Arrays.toString(arr));
        return newSt;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{6,10,5,2,6,1,0,9};
        int [] arr2 = new int[arr.length*2];
        int end =arr2.length-1, st = arr.length, tmp = -1;
        for(int i=0;i<arr.length;i++)
            arr2[i+arr.length] = arr[i];
        System.out.println(Arrays.toString(arr2));
        while( st >= 1)     {
            tmp = populate(arr2,st,end);
            end = st-1;
            st =tmp;
        }
        System.out.println(Arrays.toString(arr2));
        update (arr2 , 2+arr.length , 7 );
        System.out.println("-Updating-");
        System.out.println(Arrays.toString(arr2));
        int i1 =2+arr.length, i2 =6+arr.length;
        System.out.println("This is the max for [" +(i1-arr.length)+" ," + (i2-arr.length) + "] -> " + max(arr2,i1,i2)) ;
        i1=3+arr.length;
        i2=7+arr.length;
        System.out.println("This is the max for [" +(i1-arr.length)+" ," + (i2-arr.length) + "] -> " + max(arr2,i1,i2)) ;
        i1=3+arr.length;
        i2=6+arr.length;
        System.out.println("This is the max for [" +(i1-arr.length)+" ," + (i2-arr.length) + "] -> " + max(arr2,i1,i2)) ;
        i1=5+arr.length;
        i2=6+arr.length;
        System.out.println("This is the max for [" +(i1-arr.length)+" ," + (i2-arr.length) + "] -> " + max(arr2,i1,i2)) ;
    }

    public static void method1(String[] args) {
        int[] arr = new int[]{6,10,5,2,7,1,0,10};
//        int[] arr = new int[]{6,10};
        List<Integer> current = new ArrayList<>();
        for( int x:arr)
            current.add(x);
        List<List<Integer>> result = new ArrayList<>();
        int min = 0;
        int max = arr.length -1 ;
        result.add(current);
        while(current.size() >1) {
            ArrayList<Integer> l =  new ArrayList<Integer>();
            for (int i = min; i <=max; i = i + 2) {
                if( i +1 <= max )
                {
                    l.add(Math.max(current.get(i),current.get(i+1)));
                }else{
                    l.add(current.get(i));
                }
            }
            min = min/2;
            max = max/2;
            result.add(l);
            current = l;
        }

        for (List<Integer > res : result
             ) {
            System.out.println(res);
        }

    }

}

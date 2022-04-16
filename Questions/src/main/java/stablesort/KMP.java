package stablesort;

import java.util.Arrays;

public class KMP {

    static int[] prefixArr(String pattern){
        String[] s = pattern.split("");
        int cur = 0 ;
        int[] pref = new int[pattern.length()];
        for(int i = 1 ; i< pattern.length();i++){
            if(s[i].equals(s[cur])){
                cur++;
                pref[i] = cur;
            }else{
                cur =0 ;
            }
        }
        return pref;
    }

    public static void main(String[] args) {
        String text = "abbbbbbbbbbbbb";
        String[] t = text.split("");
        String pattern = "abbbc";
        String[] s = pattern.split("");
        int[] pref =  prefixArr(pattern);
        System.out.println(Arrays.toString(pref));
        int maxLen = Integer.MIN_VALUE,curLen = 0 ;
        for( int i=0;i< t.length;i++ ){
            if(curLen == pattern.length())
                break;
            System.out.println(" i : " + i +"[ "+t[i] + "]" +  " curLen : " + curLen + "["+s[curLen]+"]");
            if(t[i].equals(s[curLen])){
                curLen++;
                maxLen = Math.max(maxLen , curLen);
            }else{
                curLen = pref[curLen] ;
            }
        }
        System.out.println("Max  : "  + maxLen );
    }
}

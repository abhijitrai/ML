package btAnddp;

import java.util.*;

public class MaxScoreProblem {

    public static void main(String[] args) {

        int[] nums = {-5, -3, -3, -2, 7, 1}, muls = {-10, -5, 3, 4, 6};
        System.out.println("Nums -> " + Arrays.toString(nums));
        System.out.println("Muls -> " + Arrays.toString(muls));
        new MaxScoreWithGreedy().maximumScore(nums, muls);
        System.out.println("************************************");
//        new MaxScoreWithMulBackTrack1().maximumScore(nums, muls);
        System.out.println("************************************");
        new MaxScoreWithMulBackTrack2().maximumScore(nums, muls);

    }
}

/*
 * Flow :
         *                     3-0-2
         *                    /
         *               2-0-3
         *              /    \
         *             /     3-1-3
         *        1-0-4
         *        /    \        3-1-3
         *       /      \      /
         *      /        2-1-4
         * 0-0-5              \
         *     \               3-2-4
         *      \     2-1-4
         *       \   /
         *      1-1-5       3-3-5
         *           \    /
         *           2-2-5
         *                \
         *                  3-2-4
         *
*   From the above flow , we see that
*   1. The graph at end points , i.e. when the mu.length == 3 is not dependent upon future choices
*      i.e. if you have only 3 multipliers & you are calculating which number to multiply with 3
*           then there are limited options for multiplying .
*           These limits can be found via finding edge cases , in this problem the edge case was
*           a. You select only the first number all the time
*           b. You select the last number all the time
*
*   2. Using the above edge cases you can calculate the sum for 3 multiplier , since it will be multiplied with
*      a number between num[ mu.length - 1 ]( inclusive ) to  num[ num.length -1 - ( mu.length -1 ) ]
*
*   3. Once you have this boundary case fixed , next use recursion
*      At i -> 0 to mu.length -2  ,  we need to choose Max of ( mul[i] * Some Num ) + sum[ mul[i+1] * dependent number )
*      Note that the sum path is already calculated in step #1 & #2 .
*      So we can happy iterate back .
*
* 4. Also note that the cases all follow up from backtracking , its via backtracking we figured out the edge cases
*
*
*/

class MaxScoreWithDP{

    public int maximumScore(int[] nu, int[] mu) {
        int sum[][] = new int[mu.length][mu.length];
        for(int i =0 ;i< mu.length;i++ ){
            int left = i , right = nu.length -1 - ( mu.length -1 -  i ) ;

//            This was very tricky to get right .
//            So first thing , make explicit variables , i.e. left & right to reason about them .
//            How to figure out if values are correct ? Use the edge cases :


//            Edge case #1 , we were picking all the first elements ,then
//            Left should go to mu.length -1
//            Right should stay at nu.length -1
//
//            If we say that for i == mu.length -1 , we puck all first elements , then
//            left = mu.length - 1
//            right = nu.length -1 - (mu-length -1 - ( mu.length -1 ))   ( as i == mu.length -1 )
//
//            Edge case #2 , we are picking all the last elements, then
//            Left stays at 0
//            Right goes to nu.length -1 - (  mu.length  -1 )
//
//            Since at i ==  mu.length -1 we said that all first are selected, hence for i ==0 , none are
//            left = 0 ( as i ==0 )
//            right = nu.length -1 ( mu.length -1 - 0 ( as i ==0 ))
//            Thus the cases are valid


            // Choose first or Choose Last
            sum[mu.length-1][i] =
                    Math.max(
                            mu[mu.length-1] * nu[left] ,
                            mu[mu.length-1] * nu[right]
                    );
        }
        printSum(sum);

        for( int i = mu.length -2 ; i >= 0 ; i-- )
        {
            for( int j = i ; j >= 0 ; j-- )
            {
                int left = j , right = nu.length -1 - (i -  j ) ;
                // Choose first or last
                sum[i][j] =  Math.max(
                        sum[i + 1][j+1] + mu[i] * nu[left]
                        ,
                        sum[i + 1][j] + mu[i] * nu[right]
                );
            }
            printSum(sum);
        }

        return sum[0][0];

    }

    void printSum(int[][]sum){
        System.out.println("\n");
        for( int[] arr : sum)
            System.out.println(Arrays.toString(arr));

    }

}

class MaxScoreWithGreedy {

    public int maximumScore(int[] nums, int[] mul) {

        int sum[] = new int[mul.length];
        int numsRIdx = nums.length - 1, numsLIdx = 0;
        int t1 = mul[0] * nums[0], t2 = mul[0] * nums[nums.length - 1];
        sum[0] = Math.max(t1, t2);
        if (t1 < t2)
            numsRIdx--;
        else
            numsLIdx++;

        for (int i = 1; i < mul.length; i++) {
            t1 = mul[i] * nums[numsLIdx];
            t2 = mul[i] * nums[numsRIdx];
            if (t1 < t2)
                numsRIdx--;
            else
                numsLIdx++;
            sum[i] = Math.max(t1, t2) + sum[i - 1];
            System.out.println("I ->>> " + i + "\t" + Arrays.toString(sum));
        }

        return sum[mul.length - 1];

    }
}

class MaxScoreWithMulBackTrack2 {
    List<Integer> nums;
    List<Integer> multipliers;
    Map<String, Integer> cache = new HashMap<>();

    public int maximumScore(int[] numso, int[] multiplierso) {
        List<Integer> numList = new ArrayList<>();
        for (int num : numso)
            numList.add(num);
        List<Integer> mulList = new ArrayList<>();
        for (int mul : multiplierso)
            mulList.add(mul);
        nums = numList;
        multipliers = mulList;
        return backtrack2(0, 0);
    }


    /*
     * Here we reduce the number of arguments by making one ( right Idx a function of other two )
     * Flow :                    {b}
     *                     3-0-2
     *                    /
     *               2-0-3
     *              /    \
     *             /     3-1-3
     *        1-0-4
     *        /    \     3-1-3
     *       /      \   /
     *      /      2-1-4
     * 0-0-5            \       {c}
     *     \              3-2-4
     *      \     2-1-4
     *       \   /
     *      1-1-5       3-2-4
     *           \    /
     *           2-2-5
     *                \     {a}
     *                 3-3-5
     *
     *   Math.max(
                multipliers.get(mulIdx) * nums.get(numsLIdx) + backtrack2(mulIdx + 1, numsLIdx + 1)
                ,
                multipliers.get(mulIdx) * nums.get(numsRIdx) + backtrack2(mulIdx + 1, numsLIdx)
        );
     *  For the above formula when we reach an index 'i' then
     *  a. We could have reached there selecting only the first element always
     *  b. We could have reached there selecting only the last element always
     *  c. We could have reached there by selecting some combination of the first and last element
     *
     *  For instance 'i' =  3
     *  a. 0-0-5 -> 1-1-5 -> 2-2-5 -> 3-3-5
     *  b. 0-0-5 -> 1-0-4 -> 2-0-3 -> 3-0-2
     *  c. 0-0-5 -> 1-0-4 -> 2-1-4 -> 3-2-4 ...
     *
     * So If we generate all the possible combinations of 3  ( assuming 3 is our end index )
     * Then we can generate the combinations for 2
     *
     *
     * */

    int backtrack2(int mulIdx, int numsLIdx) {
        int numOfSelectionsFromLeftTillNow = numsLIdx - 0;
        int numsOfSelectionsFromRightTillNow = mulIdx - numOfSelectionsFromLeftTillNow;
        int numsRIdx = (nums.size() - 1) - numsOfSelectionsFromRightTillNow;
        if (mulIdx >= multipliers.size() || numsRIdx < numsLIdx || numsLIdx >= nums.size()
        ) {
            return 0;
        }
        String key = mulIdx + "-" + numsLIdx+"-" + numsRIdx;;
        if (cache.containsKey(key))
            return cache.get(key);
        System.out.println("Going down : Key ->  " + key + "\t Cache -> " + cache);

        int maxSum = Math.max(
                multipliers.get(mulIdx) * nums.get(numsLIdx) + backtrack2(mulIdx + 1, numsLIdx + 1)
                ,
                multipliers.get(mulIdx) * nums.get(numsRIdx) + backtrack2(mulIdx + 1, numsLIdx)
        );
        cache.put(key, maxSum);
        System.out.println("Coming up : Key ->  " + key + "\t Cache -> " + cache);

        return maxSum;
    }
}

class MaxScoreWithMulBackTrack1 {

    List<Integer> nums;
    List<Integer> multipliers;
    Map<String, Integer> cache = new HashMap<>();

    public int maximumScore(int[] numso, int[] multiplierso) {
        List<Integer> numList = new ArrayList<>();
        for (int num : numso)
            numList.add(num);
        List<Integer> mulList = new ArrayList<>();
        for (int mul : multiplierso)
            mulList.add(mul);
        nums = numList;
        multipliers = mulList;
        return backtrack(0, 0, nums.size() - 1);
    }

    /*
     *
     * Pointers for implementing the backtracking algo
     *   a. Making the function stateless - It doesnt depend on any field (  besides the cache look up )
     *   b. Reduce the number of args - the number of args determine the cache size , since we will cache for each
     *       unique combination of the args, the lower the number of arguments the smaller the combinations
     *       and the smaller the cache.
     *   c. Make the arguments simple - Do not pass things like maps / lists/sets/arrays to backtrack
     *      To search things in an sub-array / list, make the array/list a field & pass indices to the method
     *   d. Return the result( & Cache it ) - Each call to the backtrack needs to be a result for passed args
     *      So the method must return a value ,  also cache the value before returning it , so the next time
     *      we can use the cached value
     *   e. Try to find out what the arguments of the recursive method mean.
     *
     * */

    int backtrack(int mulIdx, int numsLIdx, int numsRIdx) {
        if (mulIdx >= multipliers.size() || numsRIdx < numsLIdx || numsLIdx >= nums.size()
        ) {
            return 0;
        }
        String key = mulIdx + "-" + numsLIdx + "-" + numsRIdx;

        if (cache.containsKey(key))
            return cache.get(key);

        int maxSum = Math.max(
                // multipliers.get(0) * nums.get(0)
                multipliers.get(mulIdx) * nums.get(numsLIdx)
                        +
                        backtrack(
                                mulIdx + 1, numsLIdx + 1, numsRIdx
                                // nums.subList(1,nums.size()),
                                // multipliers.subList( 1 ,multipliers.size()  )
                        )
                ,
                multipliers.get(mulIdx) * nums.get(numsRIdx)
                        // multipliers.get(0) * nums.get(nums.size() -1)
                        +
                        backtrack(
                                mulIdx + 1, numsLIdx, numsRIdx - 1
                                // nums.subList(0,nums.size()-1),
                                // multipliers.subList( 1  ,multipliers.size())
                        )
        );
        cache.put(key, maxSum);
        System.out.println("Key ->  " + key + "\t Cache -> " + cache);
        return maxSum;
    }


}

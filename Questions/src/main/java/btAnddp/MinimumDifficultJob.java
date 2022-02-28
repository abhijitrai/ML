package btAnddp;

import java.util.Arrays;

public class MinimumDifficultJob {

    /**
     * For backtracking our logic was to see which job options are available for Day1
     * Choose one option .
     * Then make a choice for the next day .
     * Once all the days have been filled , return back the best option
     */

    public static void main(String[] args) {
        int[] difficulty = {6, 5, 4, 3, 2, 1};
        int day = 4;
        System.out.println("Back Track Ans -> " + new MinimumDifficultJobBackTrack().minDifficulty(difficulty, day));
        System.out.println("DP Ans -> " + new MinimumDifficultJobDynamicProgram1().minDifficulty(difficulty, day));
    }


}


/*
 *
 * So we tried the same approach as backtracking but that didnt work
 * Approach #1 { same as back tracking }
 *
 * This approach uses the same structure as the backtracking approach of [ day <-> Job  ]
 * However we are in a problem , for e.g.
 * int[] difficulty = {6,5,4,3,2,1};    int day = 3;
 * Now lets see the options we have for the 1st index
 * These are {6 ,5,4,3 }
 * The options for the second position are {5,4,3,2}
 * The options for the last position are { 4,3,2,1 }
 * However lets consider these scenarios
 *
 * Scenario #1
 * 1 day -> { 6,5 }
 * 2 day -> { 4  }
 * 3 day -> { 3,2,1 }
 *
 * Scenarios #2
 * 1 day -> { 6 }
 * 2 day -> {5,4}
 * 3 day -> { 3,2,1 }
 *
 * In both the above cases we have the 'Index selected on day 2 ->  index '2' '
 * However we have different values for it , one is 4 the other is 5 .
 * The value at idx 2 depends on its previous values & cant be determined wholly on the future ones.
 * So we need to store this at different places , which is not possible since we have only one place for it [ 2 ][ 2]
 * So in effect we need a an array of values at index[j] & choose from that index of vlaues while fetching solutions
 *
 *
 * */

/*
 *Approach #2 -
 * So we use a different approach , in this approach we try to use the optimal solution of sub problems
 * So the logic is that for any index find the best path from that point on to the end .
 * Once we find that path always use that path when you reach that idx.
 * This is possible since we know the paths of last elements by definition ( i.e. since they are last )
 * Thus we can use this to find the path of next to last , then the one before that and then the one before that and so on
 *
 * */


class MinimumDifficultJobDynamicProgram1 {


    int[][] dayToJobMap;
    int[] hardestJobAtAnyIdx;


    int minCostAtIdx(int[] jobDifficulty, int sIdx, int endIdx, int day) {
//      At idx i , the options are take i , take i+1 , take i+2 ...till ...end IDx
//      At each step we update the count, since we have to accommodate all jobs
        int maxJD = Integer.MIN_VALUE, totalCost;
        int minCost = Integer.MAX_VALUE;
        for (int i = sIdx; i <= endIdx; i++) {
            if (maxJD < jobDifficulty[i])
                maxJD = jobDifficulty[i];
            totalCost = maxJD + dayToJobMap[day + 1][i + 1];
            if (totalCost < minCost)
                minCost = totalCost;
        }
        return minCost;
    }

    public int minDifficulty(int[] jobDifficulty, int d) {
        System.out.println(Arrays.toString(jobDifficulty));
        System.out.println(d);
        hardestJobAtAnyIdx = new int[jobDifficulty.length];
        int max = -1;
        for (int i = jobDifficulty.length - 1; i >= 0; i--) {
            if (jobDifficulty[i] > max)
                max = jobDifficulty[i];
            hardestJobAtAnyIdx[i] = max;
        }

        if (jobDifficulty.length < d)
            return -1;

        dayToJobMap = new int[d + 1][jobDifficulty.length];
        for (int i = 0; i < dayToJobMap.length; i++) {
            int[] val = dayToJobMap[i];
            for (int j = 0; j < val.length; j++) {
                dayToJobMap[i][j] = Integer.MAX_VALUE;
            }
        }
        int firstJobIdx = d - 1;
        for (int i = firstJobIdx; i < jobDifficulty.length; i++)
            dayToJobMap[d][i] = hardestJobAtAnyIdx[i];

        for (int i = d - 1; i >= 1; i--) {
            int sIdx = i - 1;
            int endIdx = (jobDifficulty.length - 1) - (d - i);
            for (int j = sIdx; j <= endIdx; j++) {
                dayToJobMap[i][j] = minCostAtIdx(jobDifficulty, j, endIdx, i);
            }

        }

//        System.out.println("----------------------------------");
//        for (int[] arr : dayToJobMap)
//            System.out.println(Arrays.toString(arr));

        return dayToJobMap[1][0];

    }


}


class MinimumDifficultJobBackTrack {


    int[] jobs;
    int d;
    int[][] dayToJobMap;
    int[] hardestJobAtAnyIdx;

    public int minDifficulty(int[] jobDifficulty, int d) {

        hardestJobAtAnyIdx = new int[jobDifficulty.length];
        int max = -1;
        for (int i = jobDifficulty.length - 1; i >= 0; i--) {
            if (jobDifficulty[i] > max)
                max = jobDifficulty[i];
            hardestJobAtAnyIdx[i] = max;
        }


        this.jobs = jobDifficulty;
        this.d = d;
        if (jobDifficulty.length < d)
            return -1;
        dayToJobMap = new int[d + 1][jobDifficulty.length];
        for (int i = 0; i < dayToJobMap.length; i++)
            for (int j = 0; j < dayToJobMap[0].length; j++)
                dayToJobMap[i][j] = Integer.MIN_VALUE;
        return backtrack(0, 1);
    }

    int backtrack(int jIdx, int day) {
        if (dayToJobMap[day][jIdx] != Integer.MIN_VALUE) {
            // System.out.println("cache  hit -> " + day +" - " + jIdx);
            return dayToJobMap[day][jIdx];
        }
        if (day == d) {
            dayToJobMap[day][jIdx] = hardestJobAtAnyIdx[jIdx];
            return dayToJobMap[day][jIdx];
        }
        int noOfDaysRes = d - day;
        int lastJobIdx = (jobs.length - 1) - noOfDaysRes;
        int minCost = Integer.MAX_VALUE, cost = -1, child = 0;
        int maxDayCost = -1;
        for (int i = jIdx; i <= lastJobIdx; i++) {
            if (maxDayCost < jobs[i])
                maxDayCost = jobs[i];
            int childCost = backtrack(i + 1, day + 1);
            cost = maxDayCost + childCost;
            if (cost < minCost) {
                minCost = cost;
            }
        }
        dayToJobMap[day][jIdx] = minCost;

        return minCost;
    }

}

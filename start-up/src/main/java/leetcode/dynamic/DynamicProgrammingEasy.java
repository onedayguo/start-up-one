package leetcode.dynamic;

/**
 * @Description: 动态规划
 * @Auther: kami
 * @Date: 2020/3/21 10:33
 * @Version: 1.0.0
 */
public class DynamicProgrammingEasy {
    /**
     * @description: 1025. Divisor Game
     * Alice and Bob take turns playing a game, with Alice starting first.
     * Initially, there is a number N on the chalkboard.  On each player's turn, that player makes a move consisting of:
     * Choosing any x with 0 < x < N and N % x == 0.
     * Replacing the number N on the chalkboard with N - x.
     * Also, if a player cannot make a move, they lose the game.
     * Return True if and only if Alice wins the game, assuming both players play optimally.
     * @return: alice是否赢得游戏
     * @auther: kami
     * @date: 2020/3/21 10:35
     */
    public boolean divisorGame(int N) {
        return  N % 2 == 0;
    }

    /**
     * @description: 121. Best Time to Buy and Sell Stock
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock),
     * design an algorithm to find the maximum profit
     * Note that you cannot sell a stock before you buy one.
     * @return: 获取最大利益
     * @auther: kami
     * @date: 2020/3/21 18:29
     */
    public static int maxProfit(int[] prices) {
        int maxCur = 0, maxSoFar = 0;
        for(int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, maxCur += prices[i] - prices[i-1]);
            maxSoFar = Math.max(maxCur, maxSoFar);
        }
        return maxSoFar;
    }
    //纪录两个状态, 一个是最大利润, 另一个是遍历过的子序列的最小值。知道之前的最小值我们就可以算出当前天可能的最大利润是多少
    public int maxProfit1(int[] prices) {
        // 7,1,5,3,6,4
        int maxProfit = 0;
        int minNum = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (Integer.MAX_VALUE != minNum && prices[i] - minNum > maxProfit) {
                maxProfit = prices[i] - minNum;
            }

            if (prices[i] < minNum) {
                minNum = prices[i];
            }
        }
        return maxProfit;
    }

    /**
     * @description: 198. House Robber
     * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money
     * stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system
     * connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
     * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount
     * of money you can rob tonight without alerting the police.
     * 解决思路：https://leetcode.com/problems/house-robber/discuss/156523/From-good-to-great.-How-to-approach-most-of-DP-problems.
     * @return: 抢劫的最大收益
     * @auther: kami
     * @date: 2020/3/22 10:13
     */
    public static int rob(int[] nums) {
        if (nums.length == 0)return 0;
        int pre1 = 0;
        int pre2 = 0;
        for (int num:nums) {
            int temp = pre1;
            pre1 = Math.max(pre1,num+pre2);
            pre2 = temp;
        }
        return pre1;
    }

    /**
     * @description: 746. Min Cost Climbing Stairs
     * @return:
     * @auther: kami
     * @date: 2020/3/22 16:17
     */
    public int minCostClimbingStairs(int[] cost) {

    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 1};
        int am = rob(arr);
        System.out.println(am);
    }
}

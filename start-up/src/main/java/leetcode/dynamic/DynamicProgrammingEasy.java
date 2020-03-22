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
     * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
     * Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top
     * of the floor, and you can either start from the step with index 0, or the step with index 1.
     * cost will have a length in the range [2, 1000].
     * Every cost[i] will be an integer in the range [0, 999].
     * 如果我们用一个数组dp[]来存放到达每一层所需要的花费值。则则最终结果是求dp[cost.length]的值。因为每次可以走1层或者2层，
     * 并且可以从0或者从1开始，所以可以得到dp[0]为0，dp[1]为0。从2开始，dp[i]可以由通过dp[i-2]走2层或者通过dp[i-1]走一层到达，
     * 而这i-2和i-1层所要花费的值分别为cost[i-2]和cost[i-1]，所以，dp[i] = min(dp[i-2] + cost[i-2], dp[i-1] + cost[i-1])。
     * 该算法的空间复杂度为O(n)，时间复杂度为O(n)。
     * @return: 到楼梯最高阶的最小花费
     * @auther: kami
     * @date: 2020/3/22 16:17
     */
    public int minCostClimbingStairs(int[] cost) {
        int pre1 = 0;
        int pre2 = 0;
        int currMin = 0;
        for (int i = 2; i < cost.length+1; i++) {
            currMin = Math.min(pre1+cost[i-1],pre2+cost[i-2]);
            pre2 = pre1;
            pre1 = currMin;
        }
        return currMin;
    }

    /**
     * @description: 392. Is Subsequence
     * Given a string s and a string t, check if s is subsequence of t.
     * You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000)
     * string, and s is a short string (<=100).
     * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none)
     * of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence
     * of "abcde" while "aec" is not).
     * @return: 是否是子字符串，不管是不是连续子字符串
     * @auther: kami
     * @date: 2020/3/22 16:45
     */
    public boolean isSubsequence(String s, String t) {
        int tIndex = 0;
        for (char currChar:s.toCharArray()) {
            int offSet = t.substring(tIndex).indexOf(currChar);
            tIndex = tIndex + offSet + 1;
            if (offSet < 0){
                return false;
            }
        }
        return true;
    }

    /**
     * @description: 1314. Matrix Block Sum
     * Given a m * n matrix mat and an integer K, return a matrix answer where each answer[i][j] is the sum of all
     * elements mat[r][c] for i - K <= r <= i + K, j - K <= c <= j + K, and (r, c) is a valid position in the matrix.
     * @return: 新的数组：原矩阵方形范围内和组成的新矩阵
     * @auther: kami
     * @date: 2020/3/22 17:20
     */
    public int[][] matrixBlockSum(int[][] mat, int K) {
        int row = mat.length,col = mat[0].length;
        int[][] matrixBlockSum = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int sum = 0;
                int rowFrom = Math.max(0,i-K);
                int rowTo = Math.min(row-1,i+K);
                int colFrom = Math.max(0,j-K);
                int colTo = Math.min(col-1,j+K);
                for (int k = rowFrom; k <= rowTo ; k++) {
                    for (int l = colFrom; l <= colTo ; l++) {
                        sum += mat[k][l];
                    }
                }
                matrixBlockSum[i][j] = sum;
            }
        }
        return matrixBlockSum;
    }   
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 1};
        int am = rob(arr);
        System.out.println(am);
    }
}

package leetcode.dynamic;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

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

    /**
     * @description: 1277. Count Square Submatrices with All Ones
     * Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
     * 思路：这是一道动态规划题
     *      1.计算以当前点为右下角的方块的数量
     *      2.累加每个点左，上，左上点方块数量
     * @return: 数字是1的方块数量
     * @auther: kami
     * @date: 2020/3/22 17:46
     */
    public int countSquares(int[][] matrix) {
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] > 0 && i > 0 && j > 0){
                    // 当前点数量 = 左，上，左上最中最小值 +1，最后+1是因为当前点值为1
                    matrix[i][j] = Math.min(matrix[i-1][j],Math.min(matrix[i][j-1],matrix[i-1][j-1])) + 1;
                }
                count += matrix[i][j];
            }
        }
        return count;
    }

    /**
     * @description: 338. Counting Bits
     * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number
     * of 1's in their binary representation and return them as an array.
     * @return: 元素1的个数组成的数组
     * @auther: kami
     * @date: 2020/3/23 22:03
     */
    public int[] countBits(int num) {
        int[] count = new int[num+1];
        for (int i = 0; i <= num ; i++) {
            int sum = 0;
            String binary = Integer.toBinaryString(i);
            for (char j:binary.toCharArray()) {
                sum += (j-'0');
            }
            count[i] = sum;
        }
        return count;
    }
    public int[] countBitsPlus(int num){
        int[] res = new int[num+1];
        for (int i = 1; i <= num ; i++) {
            res[i] = res[i>>1] + (i & 1);
        }
        return res;
    }

    /**
     * @description: 1130. Minimum Cost Tree From Leaf Values
     * Given an array arr of positive integers, consider all binary trees such that:
     * Each node has either 0 or 2 children;
     * The values of arr correspond to the values of each leaf in an in-order traversal of the tree.
     * (Recall that a node is a leaf if and only if it has 0 children.)
     * The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
     * Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.
     * It is guaranteed this sum fits into a 32-bit integer.
     * @return: 
     * @auther: kami
     * @date: 2020/3/23 22:52
     */
    public static int mctFromLeafValues(int[] A) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.MAX_VALUE);
        for (int a : A) {
            while (stack.peek() <= a) {
                int mid = stack.pop();
                res += mid * Math.min(stack.peek(), a);
            }
            stack.push(a);
        }
        while (stack.size() > 2) {
            res += stack.pop() * stack.peek();
        }
        return res;
    }

    /**
     * @description: 877. Stone Game
     * Alex and Lee play a game with piles of stones.  There are an even number of piles arranged in a row, and each pile
     * has a positive integer number of stones piles[i].
     * The objective of the game is to end with the most stones.  The total number of stones is odd, so there are no ties.
     * Alex and Lee take turns, with Alex starting first.  Each turn, a player takes the entire pile of stones from either
     * the beginning or the end of the row.  This continues until there are no more piles left, at which point the person
     * with the most stones wins.
     * Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.
     * 两个人轮流从一个数组的两头取值，谁最后取值大，谁赢
     *  Alex is first to pick pile.
     * piles.length is even, and this lead to an interesting fact:
     * Alex can always pick odd piles or always pick even piles!
     * For example,
     * If Alex wants to pick even indexed piles piles[0], piles[2], ....., piles[n-2],
     * he picks first piles[0], then Lee can pick either piles[1] or piles[n - 1].
     * Every turn, Alex can always pick even indexed piles and Lee can only pick odd indexed piles.
     * In the description, we know that sum(piles) is odd.
     * If sum(piles[even]) > sum(piles[odd]), Alex just picks all evens and wins.
     * If sum(piles[even]) < sum(piles[odd]), Alex just picks all odds and wins.
     * So, Alex always defeats Lee in this game.
     * @return: Alex是否赢
     * @auther: kami
     * @date: 2020/3/27 21:20
     */
    public boolean stoneGame(int[] p) {
        int n = p.length;
        int[][] dp  = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = p[i];
        }
        for (int d = 1; d < n; d++) {
            for (int i = 0; i < n - d; i++) {
                dp[i][i + d] = Math.max(p[i] - dp[i + 1][i + d], p[i + d] - dp[i][i + d - 1]);
            }
        }
        return dp[0][n - 1] > 0;
    }
    /**
     * @description: 1D DP
     * @return:
     * @auther: kami
     * @date: 2020/3/27 22:18
     */
    public static boolean stoneGame1(int[] p) {
        int[] dp = Arrays.copyOf(p, p.length);;
        for (int d = 1; d < p.length; d++)
            for (int i = 0; i < p.length - d; i++)
                dp[i] = Math.max(p[i] - dp[i + 1], p[i + d] - dp[i]);
        return dp[0] > 0;
    }
    /**
     * @description: 115. Distinct Subsequences
     * Given two strings s and t, return the number of distinct subsequences of s which equals t.
     * A string's subsequence is a new string formed from the original string by deleting some (can be none)
     * of the characters without disturbing the remaining characters' relative positions. (i.e., "ACE" is a
     * subsequence of "ABCDE" while "AEC" is not).
     * It is guaranteed the answer fits on a 32-bit signed integer.
     * @return: 字符串S包含字符串T的数量
     * @author: kami
     * @备注： 动态规划
     * 求S有多少个不同的子串与T相同
     * 这里我们维护res[i][j]，对应的值是S的前i个字符和T的前j个字符有多少个可行的序列。
     * 假设S的第i个字符和T的第j个字符不相同，那么就意味着res[i][j]的值跟res[i-1][j]是一样的，
     * 前面该是多少还是多少，而第i个字符的加入也不会多出来任何可行结果。如果S的第i个字符和T的第j个字符相同，
     * 那么所有res[i-1][j-1]中满足的结果都会成为新的满足的序列------------这就是我们说的常规思维的想到的
     * 当然res[i-1][j]的也仍是可行结果--------------------------------这就是我们没有想到的，我多了一个你，但是我不要你
     * 所以res[i][j]=res[i-1][j-1]+res[i-1][j]。所以综合上面两种情况，递推式应该是res[i][j]=(S[i]==T[j]?res[i-1][j-1]:0)+res[i-1][j]。
     * 算法进行两层循环，时间复杂度是O(m*n)，而空间上只需要维护当前i对应的数据就可以，也就是O(m)
     * @date: 2021/2/22 15:17
     */
    public int numDistinct(String S, String T) {
        int[][] table = new int[S.length() + 1][T.length() + 1];

        for (int i = 0; i < S.length(); i++) {
            table[i][0] = 1;
        }

        for (int i = 1; i <= S.length(); i++) {
            for (int j = 1; j <= T.length(); j++) {
                if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    table[i][j] = table[i - 1][j] + table[i - 1][j - 1];
                    //这里的table[i - 1][j]和下面的一样的意思，就是不论你是不是相等的，我都删掉你，
                    //不过万一我要了，这里就多了一个table[i - 1][j - 1] option
                } else {
                    table[i][j] = table[i - 1][j];
                }
            }
        }

        return table[S.length()][T.length()];
    }
    /**
     * @description: 120. Triangle
     * Given a triangle array, return the minimum path sum from top to bottom.
     * For each step, you may move to an adjacent number of the row below. More formally, if you are on index i
     * on the current row, you may move to either index i or index i + 1 on the next row.
     * @return: 从顶到底的最小路径
     * @author: kami
     * @备注：TODO
     * @date: 2021/2/22 19:37
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        int col = triangle.get(row-1).size();
        int[][] dp = new int[row+1][col+1];

        return 0;
    }
    /**
     * @description: 131. Palindrome Partitioning
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     * Return all possible palindrome partitioning of s.
     * A palindrome string is a string that reads the same backward as forward.
     * @return: 所有子回文字符串
     * @author: kami
     * @备注： 查找字符串的子回文字符串
     * @date: 2021/2/23 21:45
     */
    List<List<String>> partitionResult;
    ArrayList<String> currList;
    public List<List<String>> partition(String s) {
        partitionResult = new ArrayList<>();
        currList = new ArrayList<>();
        backTrackStr(s,0);
        return partitionResult;

    }

    private void backTrackStr(String s, int left){
        if (currList.size() >0 && left >= s.length()){
            partitionResult.add(new ArrayList<>(currList));
            return;
        }
        for (int i = left,length = s.length(); i < length; i++) {
            if (isPalindrome(s,left,i)){
                currList.add(s.substring(left,i+1));
                backTrackStr(s,i+1);
                currList.remove(currList.size()-1);
            }
        }

    }
    private boolean isPalindrome(String s,int left, int right){
        if (left == right){
            return true;
        }
        while (left < right){
            if (s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    public static void main(String[] args) {
        int[] arr = {6,2,4,5,6,3,2,8,5,4,1,9,6,5,4,2,8,5    };
        System.out.println(stoneGame1(arr));
    }
    
    
}

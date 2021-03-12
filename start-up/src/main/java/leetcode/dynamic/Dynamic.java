package leetcode.dynamic;

import java.util.*;

/**
 * @Description: 动态规划
 * @Auther: kami
 * @Date: 2020/3/21 10:33
 * @Version: 1.0.0
 */
public class Dynamic {
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
        return N % 2 == 0;
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
        for (int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, maxCur + (prices[i] - prices[i - 1]));
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
        if (nums.length == 0) return 0;
        int pre1 = 0;
        int pre2 = 0;
        for (int num : nums) {
            int temp = pre1;
            pre1 = Math.max(pre1, num + pre2);
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
        for (int i = 2; i < cost.length + 1; i++) {
            currMin = Math.min(pre1 + cost[i - 1], pre2 + cost[i - 2]);
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
        for (char currChar : s.toCharArray()) {
            int offSet = t.substring(tIndex).indexOf(currChar);
            tIndex = tIndex + offSet + 1;
            if (offSet < 0) {
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
        int row = mat.length, col = mat[0].length;
        int[][] matrixBlockSum = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int sum = 0;
                int rowFrom = Math.max(0, i - K);
                int rowTo = Math.min(row - 1, i + K);
                int colFrom = Math.max(0, j - K);
                int colTo = Math.min(col - 1, j + K);
                for (int k = rowFrom; k <= rowTo; k++) {
                    for (int l = colFrom; l <= colTo; l++) {
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
     * 1.计算以当前点为右下角的方块的数量
     * 2.累加每个点左，上，左上点方块数量
     * @return: 数字是1的方块数量
     * @auther: kami
     * @date: 2020/3/22 17:46
     */
    public int countSquares(int[][] matrix) {
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] > 0 && i > 0 && j > 0) {
                    // 当前点数量 = 左，上，左上最中最小值 +1，最后+1是因为当前点值为1
                    matrix[i][j] = Math.min(matrix[i - 1][j], Math.min(matrix[i][j - 1], matrix[i - 1][j - 1])) + 1;
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
        int[] count = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            int sum = 0;
            String binary = Integer.toBinaryString(i);
            for (char j : binary.toCharArray()) {
                sum += (j - '0');
            }
            count[i] = sum;
        }
        return count;
    }

    public int[] countBitsPlus(int num) {
        int[] res = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            res[i] = res[i >> 1] + (i & 1);
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
     * Alex is first to pick pile.
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
        int[][] dp = new int[n][n];
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
     * @description: 1140. Stone Game II
     * Alice and Bob continue their games with piles of stones.  There are a number of piles arranged in a row,
     * and each pile has a positive integer number of stones piles[i].  The objective of the game is to end with
     * the most stones.
     * Alice and Bob take turns, with Alice starting first.  Initially, M = 1.
     * On each player's turn, that player can take all the stones in the first X remaining piles,
     * where 1 <= X <= 2M.  Then, we set M = max(M, X).
     * The game continues until all the stones have been taken.
     * Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.
     * @return: Alice能够获取到的最大数值
     * @author: kami
     * @备注： 与887是同一类问题
     * @date: 2021/3/5 22:50
     */
    public int stoneGameII(int[] piles) {
        for (int i = piles.length - 2; i >= 0; i--) {
            piles[i] += piles[i + 1];
        }
        return dfs(piles, 1, 0, new int[piles.length][piles.length]);
    }

    private int dfs(int[] presum, int m, int p, int[][] memo) {
        if (p + 2 * m >= presum.length) { // last player takes all
            return presum[p];
        }

        if (memo[p][m] > 0) {
            return memo[p][m];
        }
        int res = 0;
        for (int i = 1, end = m << 1; i <= end; i++) {
            // take max of current + what lefts from other player max take
            int curMaxSum = presum[p] - dfs(presum, Math.max(i, m), p + i, memo);
            res = Math.max(res, curMaxSum);
        }
        memo[p][m] = res;
        return res;
    }


    /**
     * @description: 1D DP
     * @return:
     * @auther: kami
     * @date: 2020/3/27 22:18
     */
    public static boolean stoneGame1(int[] p) {
        int[] dp = Arrays.copyOf(p, p.length);
        ;
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
     * @备注：从下往上遍历
     * @date: 2021/2/22 19:37
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        int col = triangle.get(row - 1).size();
        int[][] dp = new int[row + 1][col + 1];

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
        backTrackStr(s, 0);
        return partitionResult;

    }

    private void backTrackStr(String s, int left) {
        if (currList.size() > 0 && left >= s.length()) {
            partitionResult.add(new ArrayList<>(currList));
            return;
        }
        for (int i = left, length = s.length(); i < length; i++) {
            if (isPalindrome(s, left, i)) {
                currList.add(s.substring(left, i + 1));
                backTrackStr(s, i + 1);
                currList.remove(currList.size() - 1);
            }
        }

    }

    private boolean isPalindrome(String s, int left, int right) {
        if (left == right) {
            return true;
        }
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * @Description: 123. Best Time to Buy and Sell Stock III
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * <p>
     * Design an algorithm to find the maximum profit. You may complete at most two transactions.
     * <p>
     * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
     * @Param: 价格数组
     * @Return: 最大利润
     * @Author: kami
     * @Date: 2021/2/23 10:14
     */
    public int maxProfit3(int[] prices) {
        int maxCur = 0, maxSoFar = 0;
        for (int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, maxCur + (prices[i] - prices[i - 1]));
            maxSoFar = Math.max(maxCur, maxSoFar);
        }
        return maxSoFar;
    }

    /**
     * @description: 132. Palindrome Partitioning II
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     * <p>
     * Return the minimum cuts needed for a palindrome partitioning of s.
     * @return: 将字符串切分成回文字符串的最小次数
     * @author: kami
     * @备注：动态规划
     * @date: 2021/2/25 22:10
     */
    public int minCut(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n];
        boolean[][] palind = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i] = i;
            for (int j = 0; j <= i; j++) {
                boolean same = s.charAt(i) == s.charAt(j) && (i - j < 2 || palind[j + 1][i - 1]);
                if (same) {
                    palind[j][i] = true;
                    dp[i] = j == 0 ? 0 : Math.min(dp[i], dp[j - 1] + 1);
                }
            }
        }
        return dp[n - 1];
    }

    /**
     * @description: 1641. Count Sorted Vowel Strings
     * Given an integer n, return the number of strings of length n that consist only of vowels (a, e, i, o, u)
     * and are lexicographically sorted.
     * A string s is lexicographically sorted if for all valid i, s[i] is the same as or comes before s[i+1]
     * in the alphabet.
     * @return: 按字符顺序组成的字符串的个数
     * @author: kami
     * @备注： 元音字母 a e i o u
     * @date: 2021/2/26 22:16
     */
    public int countVowelStrings(int n) {
        int a = 1;
        int e = 1;
        int i = 1;
        int o = 1;
        int u = 1;
        while (--n > 0) {
            o = o + u;
            i = i + o;
            e = e + i;
            a = a + e;
        }
        return a + e + i + o + u;
    }

    /**
     * @description: 1043. Partition Array for Maximum Sum
     * Given an integer array arr, you should partition the array into (contiguous) subarrays of length at most k.
     * After partitioning, each subarray has their values changed to become the maximum value of that subarray.
     * <p>
     * Return the largest sum of the given array after partitioning.
     * @return: 最大和
     * @author: kami
     * @备注： 动态规划
     * @date: 2021/3/1 20:55
     */
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int N = arr.length, dp[] = new int[N + 1];
        for (int i = 1; i <= N; ++i) {
            int curMax = 0, best = 0;
            for (int j = 1; j <= k && i - j >= 0; ++j) {
                curMax = Math.max(curMax, arr[i - j]);
                best = Math.max(best, dp[i - j] + curMax * j);
            }
            dp[i] = best;
        }
        return dp[N];
    }

    /**
     * @description: 931. Minimum Falling Path Sum
     * Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
     * <p>
     * A falling path starts at any element in the first row and chooses the element in the next row that is either
     * directly below or diagonally left/right. Specifically, the next element from position (row, col) will be
     * (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).
     * @return: 下落的最小路径
     * @author: kami
     * @备注： 千年的动态对话，考虑自顶向上
     * @date: 2021/3/7 22:48
     */
    public int minFallingPathSum(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        if (row == 1) {
            int min = matrix[0][0];
            for (int i = 1; i < col; i++) {
                min = Math.min(min, matrix[0][i]);
            }
            return min;
        }
        if (col == 1) {
            int sum = 0;
            for (int i = 0; i < row; i++) {
                sum += matrix[i][0];
            }
            return sum;
        }
        for (int i = row - 2; i >= 0; i--) {
            matrix[i][0] += Math.min(matrix[i + 1][0], matrix[i + 1][1]);
            matrix[i][col - 1] += Math.min(matrix[i + 1][col - 2], matrix[i + 1][col - 1]);
            for (int j = 1; j < col - 1; j++) {
                int sum = matrix[i][j];
                sum += Math.min(matrix[i + 1][j - 1], Math.min(matrix[i + 1][j], matrix[i + 1][j + 1]));
                matrix[i][j] = sum;
            }
        }
        int res = matrix[0][0];
        for (int i = 0; i < col; i++) {
            res = Math.min(res, matrix[0][i]);
        }
        return res;
    }

    /**
     * @description: 983. Minimum Cost For Tickets
     * In a country popular for train travel, you have planned some train travelling one year in advance.
     * The days of the year that you will travel is given as an array days.  Each day is an integer from 1 to 365.
     * <p>
     * Train tickets are sold in 3 different ways:
     * <p>
     * a 1-day pass is sold for costs[0] dollars;
     * a 7-day pass is sold for costs[1] dollars;
     * a 30-day pass is sold for costs[2] dollars.
     * The passes allow that many days of consecutive travel.  For example, if we get a 7-day pass on day 2,
     * then we can travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8.
     * <p>
     * Return the minimum number of dollars you need to travel every day in the given list of days.
     * @return: 旅行最少的花费
     * @author: kami
     * @备注： 动态规划，记录当前天用的最少花费
     * @date: 2021/3/7 23:23
     */
    public static int mincostTickets(int[] days, int[] costs) {
        int[] dp = new int[366];
        int curIndex = 0;
        for (int i = 1; i < 366; i++) {
            if (days[curIndex] == i) {
                int sevenDayAgo = Math.max(0, i - 7);
                int sevenMinCost = Math.min(dp[i - 1] + costs[0], dp[sevenDayAgo] + costs[1]);
                int thirtyDayAgo = Math.max(0, i - 30);
                dp[i] = Math.min(sevenMinCost, dp[thirtyDayAgo] + costs[2]);
                if (++curIndex >= days.length){
                    return dp[i];
                }
            } else {
                dp[i] = dp[i - 1];
            }
        }

        return dp[365];
    }
    /**
     * @discription 空间复杂度优化了一下
     * @date 2021/3/10 23:16
     **/
    public static int mincostTicketsOptimized(int[] days, int[] costs) {
        int[] dp = new int[30];
        int curIndex = 0;
        for (int i = 1; i < 366; i++) {
            int curI = i % 30;
            int preI = curI - 1 < 0 ? 29:curI-1;
            if (days[curIndex] == i) {
                int sevenDayAgo = Math.max(0, i - 7) % 30;
                int sevenMinCost = Math.min(dp[preI] + costs[0], dp[sevenDayAgo] + costs[1]);
                int thirtyDayAgo = Math.max(0, i - 30) % 30;
                dp[curI] = Math.min(sevenMinCost, dp[thirtyDayAgo] + costs[2]);
                if (++curIndex >= days.length){
                    return dp[curI];
                }
            } else {
                dp[curI] = dp[preI];
            }
        }
        return dp[29];
    }
    /**
     * @discription 利用队列直接录前7个旅行日和前30个旅行日
     * @date 2021/3/10 23:52
     **/
    public static int mincostTicketsTrackTravelDays(int[] days, int[] costs) {
        int cost = 0;
        Queue<DayCost> last7 = new LinkedList<>();
        Queue<DayCost> last30 = new LinkedList<>();
        for (int i = 0; i < days.length; i++) {
            while (!last7.isEmpty() && last7.peek().day+7 <= days[i]){
                last7.poll();
            }
            while (!last30.isEmpty() && last30.peek().day+30 <= days[i]){
                last30.poll();
            }
            last7.add(new DayCost(days[i],cost+costs[1]));
            last30.add(new DayCost(days[i],cost+costs[2]));
            int minTemp = Math.min(last7.peek().cost,last30.peek().cost);
            cost = Math.min(cost+costs[0],minTemp);
        }
        return cost;
    }
    static class DayCost{
        int day;
        int cost;

        public DayCost(int day, int cost) {
            this.day = day;
            this.cost = cost;
        }
    }
    /**
     * @description: 1227. Airplane Seat Assignment Probability
     * n passengers board an airplane with exactly n seats. The first passenger has lost the ticket and picks
     * a seat randomly. But after that, the rest of passengers will:
     *
     * Take their own seat if it is still available,
     * Pick other seats randomly when they find their seat occupied
     * What is the probability that the n-th person can get his own seat?
     * @return: 第N个人做到能够做到自己位置的概率
     * @author: kami
     * @备注：
     * @date: 2021/3/11 19:51
     */
    public double nthPersonGetsNthSeat(int n) {
        if (n == 1) {
            return 1.0d;
        }
        return 1d / n + (n - 2d) / n * nthPersonGetsNthSeat(n - 1);
    }
    /**
     * @Description: 1664. Ways to Make a Fair Array
     * You are given an integer array nums. You can choose exactly one index (0-indexed) and remove the element. Notice
     * that the index of the elements may change after the removal.
     * For example, if nums = [6,1,7,4,1]:
     * Choosing to remove index 1 results in nums = [6,7,4,1].
     * Choosing to remove index 2 results in nums = [6,1,4,1].
     * Choosing to remove index 4 results in nums = [6,1,7,4].
     * An array is fair if the sum of the odd-indexed values equals the sum of the even-indexed values.
     *
     * Return the number of indices that you could choose such that after the removal, nums is fair.
     * @Param:
     * @Return: 
     * @Author: kami
     * @Date: 2021/3/12 14:52
     */
    public int waysToMakeFair(int[] nums) {
        int res = 0, n = nums.length;
        int[] left = new int[2], right = new int[2];
        for (int i = 0; i < n; i++) {
            right[i%2] += nums[i];
        }
        for (int i = 0; i < n; i++) {
            right[i%2] -= nums[i];
            if (left[0]+right[1] == left[1]+right[0]) {
                res++;
            }
            left[i%2] += nums[i];
        }
        return res;

    }

    public static void main(String[] args) {
//        String cost = "10.00";
//        int end = cost.indexOf('1');
//        System.out.println(cost.substring(0,end==-1?cost.length():end));
        System.out.println(Integer.valueOf(""));;
    }

    /**
     * @discription 递归实现，LeetCode超时
     * @date 2021/3/10 22:14
     **/
    public int mincostTickets1(int[] days, int[] costs) {
        int one = costs[0], seven = costs[1], thirty = costs[2];
        int tempMin = Math.min(dfsCostTicket(days, costs, 0, one, days[0]), dfsCostTicket(days, costs, 0, seven, days[0] + 6));
        return Math.min(dfsCostTicket(days, costs, 0, thirty, days[0] + 29), tempMin);
    }

    private int dfsCostTicket(int[] days, int[] costs, int curIndex, int cost, int endDay) {
        if (curIndex >= days.length || endDay > 365) {
            return cost;
        }
        int nextIndex = curIndex + 1;
        for (int i = nextIndex, end = days.length; i < end; i++) {
            if (days[i] > endDay) {
                int tempMin = Math.min(dfsCostTicket(days, costs, i, costs[0], days[i]), dfsCostTicket(days, costs, i, costs[1], days[i] + 6));
                return cost + Math.min(dfsCostTicket(days, costs, i, costs[2], days[i] + 29), tempMin);
            }
        }
        return cost;
    }

    private static String str = "test";

//    public static void main(String[] args) {
//
//        List<String> list = new ArrayList<>();
//        while (true) {
//            String str2 = str + str;
//            str = str2;
//            System.out.println(str.intern().length());
//            list.add(str.intern());
//        }
//
//    }

    public static class SynchronizedExample {

        public void func1() {
            synchronized (this) {
                for (int i = 0; i < 10; i++) {
                    System.out.print(i + " ");
                }
            }
        }
    }

    private static class MyThread1 extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println("Thread run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class MyThread2 extends Thread {
        @Override
        public void run() {
            while (!interrupted()) {
//            while (true){

                // ..
                System.out.println("Thread ing");
            }
        }
    }


}

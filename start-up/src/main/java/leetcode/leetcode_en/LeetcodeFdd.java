package leetcode.leetcode_en;

import leetcode.interest.LeetCodeCompetition.TreeNode;


import java.util.*;

/**
 * @Description: 入职房多多两个月后的算法训练
 * @Author: kami
 * @Date: 2020/8/4 21:30
 * @Version: 1.0.0
 */
public class LeetcodeFdd {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * @description: 142. Linked List Cycle II
     * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
     * To represent a cycle in the given linked list, we use an integer pos which represents the position
     * (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
     * Note: Do not modify the linked list.
     * @return: 环链起始节点
     * @author: kami
     * @date: 2020/8/4 21:33
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        Set<ListNode> slowStepHistory = new HashSet<>();
        while (fast != null && fast.next != null && fast.next.next != null) {
            if (slowStepHistory.contains(fast.next)) {
                return fast.next;
            } else if (slowStepHistory.contains(fast.next.next)) {
                return fast.next.next;
            }
            slowStepHistory.add(slow);
            fast = fast.next.next;
            slow = slow.next;
        }
        return null;
    }

    /**
     * @description: 152. Maximum Product Subarray
     * Given an integer array nums, find the contiguous subarray within an array (containing at least one number)
     * which has the largest product.
     * @return: 连续数组最大乘积
     * @author: kami
     * @date: 2020/8/4 22:30
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = nums[0], min = nums[0], result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = max;
            max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
            min = Math.min(Math.min(temp * nums[i], min * nums[i]), nums[i]);
            if (max > result) {
                result = max;
            }
        }
        return result;
    }

    /**
     * @Description: 207. Course Schedule
     * here are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
     * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
     * which is expressed as a pair: [0,1]
     * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
     * @Param: numCourses总共要修的课程数，prerequisites课程要求
     * @Return: 能否修完课程
     * @Author: kami 2 [1,0]
     * @Date: 2020/8/20 18:16
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        boolean[] canFinish = new boolean[numCourses]; // history
        boolean[] waitingList = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!canFinishThisCourse(i, prerequisites, waitingList, canFinish)) {
                return false;
            }
        }
        return true;
    }

    public boolean canFinishThisCourse(int course, int[][] prerequisites, boolean[] waitingList, boolean[] canFinish) {
        if (canFinish[course]) {
            return true;
        }
        if (waitingList[course]) {
            return false;
        } // find circle
        // dfs backtracking
        waitingList[course] = true;
        for (int[] pair : prerequisites) {
            if (pair[0] == course) {
                if (!canFinishThisCourse(pair[1], prerequisites, waitingList, canFinish)) {
                    return false;
                }
            }
        }
        waitingList[course] = false;
        canFinish[course] = true;
        return true;
    }

    /**
     * @Description: 208. Implement Trie (Prefix Tree)
     * @Author: kami
     * @Date: 2020/8/28 10:44
     */
    class Trie {
        Set<String> set;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            this.set = new HashSet<>();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            set.add(word);
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            return set.contains(word);
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            for (String s : set) {
                if (s.startsWith(prefix)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * @Description: 212. Word Search II
     * Given a 2D board and a list of words from the dictionary, find all words in the board.
     * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
     * horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
     * Note:
     * All inputs are consist of lowercase letters a-z.
     * The values of words are distinct.
     * @Author: kami
     * @Date: 2020/9/8 9:51
     */
    public static List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, res);
            }
        }
        return res;
    }

    public static void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
        char c = board[i][j];
        if (c == '#' || p.next[c - 'a'] == null) {
            return;
        }
        p = p.next[c - 'a'];
        if (p.word != null) {   // found one
            res.add(p.word);
            p.word = null;     // de-duplicate
        }

        board[i][j] = '#';
        if (i > 0) {
            dfs(board, i - 1, j, p, res);
        }
        if (j > 0) {
            dfs(board, i, j - 1, p, res);
        }
        if (i < board.length - 1) {
            dfs(board, i + 1, j, p, res);
        }
        if (j < board[0].length - 1) {
            dfs(board, i, j + 1, p, res);
        }
        board[i][j] = c;
    }

    public static TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) {
                    p.next[i] = new TrieNode();
                }
                p = p.next[i];
            }
            p.word = w;
        }
        return root;
    }

    static class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }

    /**
     * @Description: 221. Maximal Square
     * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
     * 解决思路：动态规划，从第2行第2列的点开始遍历，记录每个以此点为组成矩形的右下角，如果组不成矩形就记录为0，否则记录为1，依次累加，得到最终组成矩形的长度
     * @Param: 01矩阵
     * @Return: 1组成的最大矩形的1的数量
     * @Author: kami
     * @Date: 2020/9/21 16:49
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int max = 0, n = matrix.length, m = matrix[0].length;

        // dp(i, j) represents the length of the square
        // whose lower-right corner is located at (i, j)
        // dp(i, j) = min{ dp(i-1, j-1), dp(i-1, j), dp(i, j-1) }
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    int leftUp = dp[i - 1][j - 1];
                    int left = dp[i][j - 1];
                    int up = dp[i - 1][j];
                    dp[i][j] = getMinValue(leftUp, left, up) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        // return the area
        return max * max;
    }

    private int getMinValue(int value1, int value2, int value3) {
        return Math.min(value1, Math.min(value2, value3));
    }

    /**
     * @Description: 236. Lowest Common Ancestor of a Binary Tree寻找二叉树中两个节点最近的公共祖先
     * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
     * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and
     * q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
     * 最近公共祖先定义为：节点T同时有P和Q作为子节点，且T到P，Q的距离之和最小，这个T节点可能是P和Q中的一个，因为自身也是自身的一个子节点
     * @Author: kami
     * @Date: 2020/10/24 12:33
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

    /**
     * @Description: 92. Reverse Linked List II翻转连表
     * Reverse a linked list from position m to n. Do it in one-pass.
     * Note: 1 ≤ m ≤ n ≤ length of list.
     * @Author: kami
     * @Date: 2020/10/26 13:35
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0); // create a dummy node to mark the head of this list
        dummy.next = head;
        ListNode pre = dummy; // make a pointer pre as a marker for the node before reversing
        for (int i = 0; i < m - 1; i++) pre = pre.next;

        ListNode start = pre.next; // a pointer to the beginning of a sub-list that will be reversed
        ListNode then = start.next; // a pointer to a node that will be reversed

        // 1 - 2 -3 - 4 - 5 ; m=2; n =4 ---> pre = 1, start = 2, then = 3
        // dummy-> 1 -> 2 -> 3 -> 4 -> 5

        for (int i = 0; i < n - m; i++) {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }

        // first reversing : dummy->1 - 3 - 2 - 4 - 5; pre = 1, start = 2, then = 4
        // second reversing: dummy->1 - 4 - 3 - 2 - 5; pre = 1, start = 2, then = 5 (finish)

        return dummy.next;
    }

    /**
     * @Description: 95. Unique Binary Search Trees II
     * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
     * @Param: 数字N
     * @Return: 存储数字1-N的所有二叉树
     * @Author: kami
     * @Date: 2020/10/26 16:35
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new LinkedList<>();
        return genTreeList(1, n);
    }

    private List<TreeNode> genTreeList(int start, int end) {
        List<TreeNode> list = new LinkedList<>();
        if (start > end) {
            list.add(null);
        }
        for (int idx = start; idx <= end; idx++) {
            List<TreeNode> leftList = genTreeList(start, idx - 1);
            List<TreeNode> rightList = genTreeList(idx + 1, end);
            for (TreeNode left : leftList) {
                for (TreeNode right : rightList) {
                    list.add(new TreeNode(idx, left, right));
                }
            }
        }
        return list;
    }

    /**
     * @Description: 97. Interleaving String 交叉字符串
     * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
     * An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:
     * s = s1 + s2 + ... + sn
     * t = t1 + t2 + ... + tm
     * |n - m| <= 1
     * The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
     * Note: a + b is the concatenation of strings a and b.
     * @Param: 三个字符串
     * @Return: S3是否是S1和S2交叉组合的
     * @Author: kami
     * @Date: 2020/10/26 18:22
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int s1Len = s1.length(), s2Len = s2.length(), s3Len = s3.length();
        if (s1Len + s2Len != s3Len) {
            return false;
        }
        return recursiveString(s1, 0, s1Len, s2, 0, s2Len, s3, 0, s3Len);
    }

    private boolean recursiveString(String s1, int s1Index, int s1Len, String s2, int s2Index, int s2Len,
                                    String s3, int s3Index, int s3Len) {
        if (s3Index >= s3Len) {
            return true;
        }
        if (s1Index < s1Len && s1.charAt(s1Index) == s3.charAt(s3Index)) {
            boolean s1Search = recursiveString(s1, s1Index + 1, s1Len, s2, s2Index, s2Len, s3, s3Index + 1, s3Len);
            if (s1Search) {
                return true;
            }
        }
        if (s2Index < s2Len && s2.charAt(s2Index) == s3.charAt(s3Index)) {
            boolean s2Search = recursiveString(s1, s1Index, s1Len, s2, s2Index + 1, s2Len, s3, s3Index + 1, s3Len);
            if (s2Search) {
                return true;
            }
        }
        return false;
    }

    /**
     * @description: 240. Search a 2D Matrix II 在有序的矩阵中寻找特定数
     * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following
     * properties:
     * Integers in each row are sorted in ascending from left to right.
     * Integers in each column are sorted in ascending from top to bottom.
     * Example:
     * <p>
     * Consider the following matrix:
     * <p>
     * [
     * [1,   4,  7, 11, 15],
     * [2,   5,  8, 12, 19],
     * [3,   6,  9, 16, 22],
     * [10, 13, 14, 17, 24],
     * [18, 21, 23, 26, 30]
     * ]
     * Given target = 5, return true.
     * <p>
     * Given target = 20, return false.
     * @return:
     * @author: kami
     * @date: 2020/11/21 21:09
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }
        int col = matrix[0].length - 1;
        int row = 0;
        while (col >= 0 && row <= matrix.length - 1) {
            if (target == matrix[row][col]) {
                return true;
            } else if (target < matrix[row][col]) {
                col--;
            } else if (target > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }

    /**
     * @description: 279. Perfect Squares
     * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...)
     * which sum to n.
     * <p>
     * Example 1:
     * <p>
     * Input: n = 12
     * Output: 3
     * Explanation: 12 = 4 + 4 + 4.
     * Example 2:
     * <p>
     * Input: n = 13
     * Output: 2
     * Explanation: 13 = 4 + 9.
     * @return:
     * @author: kami
     * @date: 2020/11/22 0:13
     */
    public static int numSquares(int n) {
        // e.g.if n = 10, for 1, the number of of squares of 1 + 2 * 2, 1 + 3 * 3 , 1 + 4 * 4.... are all temporarily
        // dp[1] + 1. We keep looping and updating the dp value and finally get the smallest one.
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 1; i + j * j <= n; j++) {
                dp[i + j * j] = Math.min(dp[i + j * j], dp[i] + 1);
            }
        }
        return dp[n];
    }

    /**
     * @description: 295. Find Median from Data Stream
     * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle
     * value. So the median is the mean of the two middle value.
     * <p>
     * For example,
     * [2,3,4], the median is 3
     * <p>
     * [2,3], the median is (2 + 3) / 2 = 2.5
     * <p>
     * Design a data structure that supports the following two operations:
     * <p>
     * void addNum(int num) - Add a integer number from the data stream to the data structure.
     * double findMedian() - Return the median of all elements so far.
     * @return:
     * @author: kami
     * @date: 2020/11/22 11:30
     */
    class MedianFinder {
        private PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());
        private PriorityQueue<Integer> large = new PriorityQueue<>();
        private boolean even = true;

        public double findMedian() {
            if (even) {
                return (small.peek() + large.peek()) / 2.0;
            } else {
                return small.peek();
            }
        }

        public void addNum(int num) {
            if (even) {
                large.offer(num);
                small.offer(large.poll());
            } else {
                small.offer(num);
                large.offer(small.poll());
            }
            even = !even;
        }
    }

    /**
     * @description: 300. Longest Increasing Subsequence
     * Given an integer array nums, return the length of the longest strictly increasing subsequence.
     * A subsequence is a sequence that can be derived from an array by deleting some or no elements without
     * changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array
     * [0,3,1,6,2,2,7].
     * Example 1:
     * Input: nums = [10,9,2,5,3,7,101,18]
     * Output: 4
     * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
     * @return: 升序的长度
     * @author: kami
     * @date: 2020/11/22 12:05
     */
    public int lengthOfLIS(int[] nums) {
        /**
         * @description: dp[i] 表示以 nums[i] 为结尾的最长递增子串的长度，对于每一个 nums[i]，从第一个数再搜索到i，如果发现某个数小于
         * nums[i]，更新 dp[i]，更新方法为 dp[i] = max(dp[i], dp[j] + 1)，即比较当前 dp[i] 的值和那个小于 num[i] 的数的 dp
         * 值加1的大小，就这样不断的更新 dp 数组，到最后 dp 数组中最大的值就是我们要返回的 LIS 的长度
         * @return: int
         * @author: kami
         * @date: 2020/11/22 20:16
         */
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public int lengthOfLIS1(int[] nums) {
        /**
         * @description: 二分查找法
         * 先建立一个数组 ends，把首元素放进去，然后比较之后的元素，如果遍历到的新元素比 ends 数组中的首元素小的话，替换首元素为此新元素，
         * 如果遍历到的新元素比 ends 数组中的末尾元素还大的话，将此新元素添加到 ends 数组末尾(注意不覆盖原末尾元素)。如果遍历到的新元素比
         * ends 数组首元素大，比尾元素小时，此时用二分查找法找到第一个不小于此新元素的位置，覆盖掉位置的原来的数字，以此类推直至遍历完整个
         * nums 数组，此时 ends 数组的长度就是要求的LIS的长度，特别注意的是 ends 数组的值可能不是一个真实的 LIS，比如若输入数组 nums
         * 为 {4, 2， 4， 5， 3， 7}，那么算完后的 ends 数组为 {2， 3， 5， 7}，可以发现它不是一个原数组的 LIS，只是长度相等而已，
         * 千万要注意这点。
         * https://www.cnblogs.com/grandyang/p/4938187.html
         * @return: int
         * @author: kami
         * @date: 2020/11/22 20:25
         */
        LinkedList<Integer> ends = new LinkedList<>();
        ends.add(nums[0]);
        int head = nums[0];
        int tail = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < head) {
                ends.addFirst(nums[i]);
                head = nums[i];
            } else if (nums[i] > tail) {
                ends.addLast(nums[i]);
                tail = nums[i];
            } else {
                int size = ends.size();
                for (int j = 0; j < size; j++) {
                    if (ends.get(j) >= nums[i]) {
                        ends.set(j, nums[i]);
                    }
                }
            }
        }
        return ends.size();
    }

    /**
     * @Description: 309. Best Time to Buy and Sell Stock with Cooldown 最优买卖股票
     * 思考：可以从动态规划的角度出发
     * 从第一天开始，计算到当前这一天的最大利润； 又因为隔天才能卖出，所以还需要在保存前一天的最大赢利
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one
     * and sell one share of the stock multiple times) with the following restrictions:
     * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
     * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
     * Example:
     * @Author: kami
     * @Date: 2020/12/26 17:18
     */
    public static int maxProfit(int[] prices) {
        // 2^31 - 1
        int buy = Integer.MAX_VALUE;
        int pfree = 0, last = 0, now = 0;
        for (int x : prices) {
            now = Math.max(last, x - buy);
            buy = Math.min(buy, x - pfree);
            pfree = last;
            last = now;
        }
        return now;
    }

    /**
     * @Description: 322. Coin Change
     * You are given coins of different denominations and a total amount of money amount. Write a function to compute the
     * fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any
     * combination of the coins, return -1.
     * You may assume that you have an infinite number of each kind of coin.
     * @Author: kami
     * @Date: 2021/1/7 11:19
     */
    public int coinChange(int[] coins, int amount) {
        // dp[i]表示组成金额i需要的最少硬币数
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for(int j = 0; j < coins.length; j++){
            for(int i = 0; i <= amount; i++){
                // 所求金额大于当前硬币面额才更新
                if(i - coins[j] >= 0) {
                    // 所求金额需要的最小硬币数是 1.上次计算出来的数值 2.目标金额减去当前硬币面额所用的最小硬币数 + 1
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
    /**
     * @Description: 60. Permutation Sequence
     * @Author: kami
     * @Date: 2021/1/12 17:35
     */
    public String getPermutation(int n, int k) {
        int[] factorial = new int[n+1];
        factorial[0]=1;
        int sum = 1;
        List<Integer> number = new ArrayList<>();
        for (int i = 1; i <=n ; i++) {
            sum *= i;
            factorial[i] = sum;
            number.add(i);
        }
        k--;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int index = k/factorial[n-i];
            sb.append(number.get(index));
            number.remove(index);
            k-=index*factorial[n-i];
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        int[] prices = {3, 4, 5, 6, 2, 0, 3};
        System.out.println(maxProfit(prices));
    }
}

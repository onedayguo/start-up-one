package leetcode;

import leetcode.tree.Tree;

import java.sql.SQLOutput;
import java.util.*;

/**
 * @Description: LeetCode最受欢迎100题
 * @Auther: kami
 * @Date: 2020/4/12 16:08
 * @Version: 1.0.0
 */
public class LeetCodeTop100 {
    /**
     * @description: 139. Word Break 中等难度
     * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
     * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
     * Note:
     * The same word in the dictionary may be reused multiple times in the segmentation.
     * You may assume the dictionary does not contain duplicate words.
     * Input: s = "leetcode", wordDict = ["leet", "code"]
     * Output: true
     * Explanation: Return true because "leetcode" can be segmented as "leet code".
     * @return:
     * @auther: kami
     * @date: 2020/4/12 16:09
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        //此方法时间超过限制
        int size = wordDict.size();
        return helperWordBreak(s,wordDict,size);
    }
    private static boolean helperWordBreak(String newStr,List<String> wordDict,int size){
        if (newStr == null || newStr.length() == 0) return true;
        for (int i = 0; i < size; i++) {
            boolean flag = newStr.startsWith(wordDict.get(i));
            if (flag){
                int iSize = wordDict.get(i).length();
                String nextStr = newStr.substring(iSize);
                if (helperWordBreak(nextStr,wordDict,size)){
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * @description: 动态规划
     * @return:
     * @auther: kami
     * @date: 2020/4/12 16:42
     */
    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0) return false;

        int n = s.length();

        // dp[i] represents whether s[0...i] can be formed by dict
        boolean[] dp = new boolean[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                String sub = s.substring(j, i + 1);

                if (dict.contains(sub) && (j == 0 || dp[j - 1])) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n - 1];
    }

    class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }
    /**
     * @description: 98. Validate Binary Search Tree中等难度
     * Given a binary tree, determine if it is a valid binary search tree (BST).
     * Assume a BST is defined as follows:
     * The left subtree of a node contains only nodes with keys less than the node's key.
     * The right subtree of a node contains only nodes with keys greater than the node's key.
     * Both the left and right subtrees must also be binary search trees.
     * @return:
     * @auther: kami
     * @date: 2020/4/12 23:40
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;
        if (root.val >= maxVal || root.val <= minVal) return false;
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }

    /**
     * @description: 96. Unique Binary Search Trees 动态规划，中等难度
     * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
     * The problem can be solved in a dynamic programming way. I’ll explain the intuition and formulas in the following.
     * Given a sequence 1…n, to construct a Binary Search Tree (BST) out of the sequence, we could enumerate each number
     * i in the sequence, and use the number as the root, naturally, the subsequence 1…(i-1) on its left side would lay on
     * the left branch of the root, and similarly the right subsequence (i+1)…n lay on the right branch of the root. We then
     * can construct the subtree from the subsequence recursively. Through the above approach, we could ensure that the BST
     * that we construct are all unique, since they have unique roots.
     * The problem is to calculate the number of unique BST. To do so, we need to define two functions:
     * G(n): the number of unique BST for a sequence of length n.
     * F(i, n), 1 <= i <= n: the number of unique BST, where the number i is the root of BST, and the sequence ranges from 1 to n.
     * As one can see, G(n) is the actual function we need to calculate in order to solve the problem. And G(n) can be
     * derived from F(i, n), which at the end, would recursively refer to G(n).
     * First of all, given the above definitions, we can see that the total number of unique BST G(n), is the sum of BST F(i) using each number i as a root.
     * i.e.
     * G(n) = F(1, n) + F(2, n) + ... + F(n, n).
     * Particularly, the bottom cases, there is only one combination to construct a BST out of a sequence of length 1
     * (only a root) or 0 (empty tree).
     * i.e.
     * G(0)=1, G(1)=1.
     * Given a sequence 1…n, we pick a number i out of the sequence as the root, then the number of unique BST with the
     * specified root F(i), is the cartesian product of the number of BST for its left and right subtrees. For example,
     * F(3, 7): the number of unique BST tree with number 3 as its root. To construct an unique BST out of the entire
     * sequence [1, 2, 3, 4, 5, 6, 7] with 3 as the root, which is to say, we need to construct an unique BST out of
     * its left subsequence [1, 2] and another BST out of the right subsequence [4, 5, 6, 7], and then combine them
     * together (i.e. cartesian product). The tricky part is that we could consider the number of unique BST out of
     * sequence [1,2] as G(2), and the number of of unique BST out of sequence [4, 5, 6, 7] as G(4). Therefore, F(3,7) = G(2) * G(4).
     * i.e.
     * F(i, n) = G(i-1) * G(n-i)	1 <= i <= n
     * Combining the above two formulas, we obtain the recursive formula for G(n). i.e.
     * G(n) = G(0) * G(n-1) + G(1) * G(n-2) + … + G(n-1) * G(0)
     * In terms of calculation, we need to start with the lower number, since the value of G(n) depends on the values of G(0) … G(n-1).
     * With the above explanation and formulas, here is the implementation in Java.
     * @return: 
     * @auther: kami
     * @date: 2020/4/14 14:29
     */
    public int numTrees(int n) {
        int[] sum = new int[n+1];
        sum[0] = 1;
        sum[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i ; j++) {
                sum[i] += (sum[j-1]*sum[i-j]);
            }
        }
        return sum[n];
    }

    /**
     * @description: 347. Top K Frequent Elements
     * Given a non-empty array of integers, return the k most frequent elements.
     * @return: 出现次数最多的k个数的数组
     * @auther: kami
     * @date: 2020/4/17 7:34
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        // build hash map : character and how often it appears
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int n: nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // init heap 'the less frequent element first'
        PriorityQueue<Integer> heap =
                new PriorityQueue<Integer>((n1, n2) -> count.get(n1) - count.get(n2));

        // keep k top frequent elements in the heap
        for (int n: count.keySet()) {
            heap.add(n);
            if (heap.size() > k)
                heap.poll();
        }

        // build output list
        List<Integer> top_k = new LinkedList<>();
        while (!heap.isEmpty())
            top_k.add(heap.poll());
        Collections.reverse(top_k);
        return top_k;
    }

    /**
     * @description: 494. Target Sum
     * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -.
     * For each integer, you should choose one from + and - as its new symbol.
     * Find out how many ways to assign symbols to make sum of integers equal to target S.
     * @return: 运算后得到目标值的 方式种类
     * @auther: kami
     * @date: 2020/4/18 23:06
     */
    int count = 0;
    public int findTargetSumWays(int[] nums, int S) {
//        calculateBruteForce(nums,0,0,S);
        int sum = 0;
        for (int n : nums)
            sum += n;
        return sum < S || (S + sum) % 2 > 0 ? 0 : subsetSum(nums, (S + sum) >>> 1);

    }
    /**
     * @description: 494. Target Sum,暴力求解法
     * @return:
     * @auther: kami
     * @date: 2020/4/19 8:11
     */
    private void calculateBruteForce(int[] nums,int index,int sum,int S){
        if (index == nums.length && sum == S)count++;
        else {
            calculateBruteForce(nums,index+1,sum+nums[index],S);
            calculateBruteForce(nums,index+1,sum-nums[index],S);
        }
    }

    /**
     * @description: 494. Target Sum动态规划
     * the recursive solution is very slow, because its runtime is exponential
     * The original problem statement is equivalent to:
     * Find a subset of nums that need to be positive, and the rest of them negative, such that the sum is equal to target
     * Let P be the positive subset and N be the negative subset
     * For example:
     * Given nums = [1, 2, 3, 4, 5] and target = 3 then one possible solution is +1-2+3-4+5 = 3
     * Here positive subset is P = [1, 3, 5] and negative subset is N = [2, 4]
     * Then let's see how this can be converted to a subset sum problem:
     *                   sum(P) - sum(N) = target
     * sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
     *                        2 * sum(P) = target + sum(nums)
     * So the original problem has been converted to a subset sum problem as follows:
     * Find a subset P of nums such that sum(P) = (target + sum(nums)) / 2
     * Note that the above formula has proved that target + sum(nums) must be even
     * We can use that fact to quickly identify inputs that do not have a solution (Thanks to @BrunoDeNadaiSarnaglia for the
     * suggestion)
     * For detailed explanation on how to solve subset sum problem, you may refer to Partition Equal Subset Sum
     * @return:
     * @auther: kami
     * @date: 2020/4/19 9:38
     */
    public int subsetSum(int[] nums, int s) {
        int[] dp = new int[s + 1];
        dp[0] = 1;
        for (int n : nums)
            for (int i = s; i >= n; i--)
                dp[i] += dp[i - n];
        return dp[s];
    }


    public static void main(String[] args) {
        String s = "cars";
        List<String> list = new ArrayList<>();
        list.add("car");
        list.add("ca");
        list.add("rs");
        System.out.println(wordBreak(s,list));
    }
}

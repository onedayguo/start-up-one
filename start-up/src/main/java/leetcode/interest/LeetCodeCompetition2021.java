package leetcode.interest;

import leetcode.LeetCodeTop100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Description: 周挑战
 * @Author: kami
 * @Date: 2021/5/19 20:33
 * @Version: 1.0.0
 */
public class LeetCodeCompetition2021 {
    /**
     * @description: Minimum Moves to Equal Array Elements II
     * Given an integer array nums of size n, return the minimum number of moves required to
     * make all array elements equal.
     * <p>
     * In one move, you can increment or decrement an element of the array by 1.
     * @return: 需要多少步使所有数字相等
     * @author: kami
     * @关键词： 排序
     * @date: 2021/5/19 20:33
     */
    public int minMoves2(int[] nums) {
        // 此版本超时
        int min = nums[0], max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        int minStep = Integer.MAX_VALUE;
        for (int i = min; i <= max; i++) {
            int tempSum = 0;
            for (int j = 0; j < nums.length; j++) {
                tempSum += Math.abs(i - nums[j]);
            }
            minStep = Math.min(minStep, tempSum);
        }
        return minStep;
    }

    public int minMoves21(int[] nums) {
        Arrays.sort(nums);
        int i = 0, j = nums.length - 1;
        int count = 0;
        while (i < j) {
            count += nums[j] - nums[i];
            i++;
            j--;
        }
        return count;
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * @description: Binary Tree Level Order Traversal
     * Given the root of a binary tree, return the level order traversal of its nodes' values.
     * (i.e., from left to right, level by level).
     * @return: 层序遍历列表
     * @author: kami
     * @关键词： 列表
     * @date: 2021/5/20 15:29
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> wrapList = new LinkedList<>();

        if (root == null) {
            return wrapList;
        }

        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            List<Integer> subList = new LinkedList<>();
            for (int i = 0; i < levelNum; i++) {
                if (queue.peek().left != null) {
                    queue.offer(queue.peek().left);
                }
                if (queue.peek().right != null) {
                    queue.offer(queue.peek().right);
                }
                subList.add(queue.poll().val);
            }
            wrapList.add(subList);
        }
        return wrapList;
    }
    /**
     * @description: Find and Replace Pattern
     * Given a list of strings words and a string pattern, return a list of words[i] that match pattern.
     * You may return the answer in any order.
     *
     * A word matches the pattern if there exists a permutation of letters p so that after replacing every
     * letter x in the pattern with p(x), we get the desired word.
     *
     * Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another
     * letter, and no two letters map to the same letter.
     * @return: 符合格式的单词列表
     * @author: kami
     * @关键词： 匹配
     * @date: 2021/5/22 15:39
     */
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        int[] p = F(pattern);
        List<String> res = new ArrayList<String>();
        for (String w : words) {
            if (Arrays.equals(F(w), p)) {
                res.add(w);
            }
        }
        return res;
    }

    public int[] F(String w) {
        HashMap<Character, Integer> m = new HashMap<>();
        int n = w.length();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            m.putIfAbsent(w.charAt(i), m.size());
            res[i] = m.get(w.charAt(i));
        }
        return res;
    }
}

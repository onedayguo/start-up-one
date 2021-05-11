package leetcode;

import org.w3c.dom.ranges.Range;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

/**
 * @Description: LeetCode面试最多, 由简单到中等再到困难
 * @Author: kami
 * @Date: 2021/4/26 11:37
 * @Version: 1.0.0
 */
public class TopInterview {
    /**
     * @description: 190. Reverse Bits
     * Reverse bits of a given 32 bits unsigned integer.
     * Note:
     * Note that in some languages such as Java, there is no unsigned integer type. In this case,
     * both input and output will be given as a signed integer type. They should not affect your implementation,
     * as the integer's internal binary representation is the same, whether it is signed or unsigned.
     * In Java, the compiler represents the signed integers using 2's complement notation. Therefore,
     * in Example 2 above, the input represents the signed integer -3 and the output represents the signed
     * integer -1073741825.
     * Follow up:
     * If this function is called many times, how would you optimize it?
     * @return: 翻转比特后的数字
     * @author: kami
     * @关键词： 移位
     * @date: 2021/4/26 11:38
     */
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            // 左移扩大
            res = (res << 1) | (n & 1);
            n = n >> 1;
        }
        return res;
    }

    /**
     * @description: 326. Power of Three
     * Given an integer n, return true if it is a power of three. Otherwise, return false.
     * An integer n is a power of three, if there exists an integer x such that n == 3x.
     * @return: n是否是3的幂次
     * @author: kami
     * @关键词： 循环除以3
     * @date: 2021/4/26 12:37
     */
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        while (n > 1) {
            if (n % 3 != 0) {
                return false;
            }
            n /= 3;
        }
        return true;
    }

    /**
     * @description: 172. Factorial Trailing Zeroes
     * Given an integer n, return the number of trailing zeroes in n!.
     * Follow up: Could you write a solution that works in logarithmic time complexity?
     * @return: 阶乘值0的个数
     * @author: kami
     * @关键词： 数一下5的个数
     * @date: 2021/4/26 12:49
     */
    public static int trailingZeroes(int n) {
        int cntFive = 0;
        int cur = 5;
        while (cur <= n) {
            int value = cur;
            while (value % 5 == 0) {
                cntFive++;
                value /= 5;
            }
            cur += 5;
        }
        return cntFive;
    }

    public static int trailingZeros1(int n) {
        return n == 0 ? 0 : n / 5 + trailingZeros1(n / 5);
    }

    /**
     * @description: 204. Count Primes
     * Count the number of prime numbers less than a non-negative number, n.
     * @return: 小于N的质数的个数
     * @author: kami
     * @关键词：
     * @date: 2021/4/27 11:22
     */
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (!isPrime[i]) {
                cnt++;
                for (int j = 2 * i; j < n; j += i) {
                    isPrime[j] = true;
                }
            }
        }
        return cnt;
    }

    /**
     * @description: 289. Game of Life
     * According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised
     * by the British mathematician John Horton Conway in 1970."
     * <p>
     * The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1)
     * or dead (represented by a 0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal)
     * using the following four rules (taken from the above Wikipedia article):
     * Any live cell with fewer than two live neighbors dies as if caused by under-population.
     * Any live cell with two or three live neighbors lives on to the next generation.
     * Any live cell with more than three live neighbors dies, as if by over-population.
     * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
     * The next state is created by applying the above rules simultaneously to every cell in the current state,
     * where births and deaths occur simultaneously. Given the current state of the m x n grid board, return the
     * next state.
     * @return: 下一代的状态数组
     * @author: kami
     * @关键词： 开辟新数组记录下一代状态
     * @date: 2021/4/27 12:25
     */
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length, n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lives = liveNeighbors(board, m, n, i, j);

                // In the beginning, every 2nd bit is 0;
                // So we only need to care about when will the 2nd bit become 1.
                if (board[i][j] == 1 && lives >= 2 && lives <= 3) {
                    board[i][j] = 3; // Make the 2nd bit 1: 01 ---> 11
                }
                if (board[i][j] == 0 && lives == 3) {
                    board[i][j] = 2; // Make the 2nd bit 1: 00 ---> 10
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;  // Get the 2nd state.
            }
        }
    }

    public int liveNeighbors(int[][] board, int m, int n, int i, int j) {
        int lives = 0;
        for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, m - 1); x++) {
            for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n - 1); y++) {
                lives += board[x][y] & 1;
            }
        }
        lives -= board[i][j] & 1;
        return lives;
    }

    /**
     * @description: 415. Add Strings
     * Given two non-negative integers, num1 and num2 represented as string,
     * return the sum of num1 and num2 as a string.
     * Input: num1 = "11", num2 = "123"
     * Output: "134"
     * @return: 字符串数字相加
     * @author: kami
     * @关键词： 累加，进位
     * @date: 2021/4/28 21:41
     */
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        // 计算完以后的答案需要翻转过来
        ans.reverse();
        return ans.toString();
    }

    /**
     * @description: 378. Kth Smallest Element in a Sorted Matrix
     * Given an n x n matrix where each of the rows and columns are sorted in ascending order,
     * return the kth smallest element in the matrix.
     * <p>
     * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
     * @return: 第K小的数
     * @author: kami
     * @关键词：
     * @date: 2021/5/4 14:51
     */
    public int kthSmallest(int[][] matrix, int k) {
        int lo = matrix[0][0], hi = matrix[matrix.length - 1][matrix[0].length - 1] + 1;//[lo, hi)
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int count = 0, j = matrix[0].length - 1;
            for (int i = 0; i < matrix.length; i++) {
                while (j >= 0 && matrix[i][j] > mid) {
                    j--;
                }
                count += (j + 1);
            }
            if (count < k) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    /**
     * @description: 454. 4Sum II
     * Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the number of
     * tuples (i, j, k, l) such that:
     * 0 <= i, j, k, l < n
     * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
     * @return: 四数相加和为0的个数
     * @author: kami
     * @关键词： map记录搭配数量
     * @date: 2021/5/5 10:08
     */
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> num12 = new HashMap<>();
        Map<Integer, Integer> num34 = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int sum = nums1[i] + nums2[j];
                num12.put(sum, num12.getOrDefault(sum, 0) + 1);
            }
        }
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                int sum = nums3[i] + nums4[j];
                num34.put(sum, num34.getOrDefault(sum, 0) + 1);
            }
        }
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : num12.entrySet()) {
            int cnt = entry.getValue();
            res += (cnt * num34.getOrDefault(-entry.getKey(), 0));
        }
        return res;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    /**
     * @description: 103. Binary Tree Zigzag Level Order Traversal
     * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left
     * to right, then right to left for the next level and alternate between).
     * @return: 层序遍历的列表
     * @author: kami
     * @关键词： 广度优先遍历，使用队列，临时队列
     * @date: 2021/5/5 10:46
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean reverse = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            /* use linkedlist to control time complexity of addFirst to O(1) */
            LinkedList<Integer> tempList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                if (reverse) {
                    tempList.addFirst(curNode.val);
                }
                else {
                    tempList.add(curNode.val);
                }

                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }
            result.add(tempList);
            reverse = !reverse;
        }
        return result;
    }
    /**
     * @description: 371. Sum of Two Integers
     * Given two integers a and b, return the sum of the two integers without using the operators + and -.
     * @return: a+b的值，不能使用 + -
     * @author: kami
     * @关键词： Use ^ to remove even exactly same numbers and save the odd, or save the distinct bits and remove the same.
     * @date: 2021/5/5 13:08
     */
    public int getSum(int a, int b) {
        int c;
        while(b !=0 ) {
            c = (a&b);
            a = a ^ b;
            b = (c)<<1;
        }
        return a;
    }
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
    /**
     * @description: 116. Populating Next Right Pointers in Each Node
     * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children.
     * The binary tree has the following definition:
     * opulate each next pointer to point to its next right node. If there is no next right node,
     * the next pointer should be set to NULL.
     *
     * Initially, all next pointers are set to NULL.
     * @return: 填充了next指针的头结点
     * @author: kami
     * @关键词： 广度优先遍历
     * @date: 2021/5/5 14:31
     */
    public Node connect(Node root) {
        Queue<Node> queue = new LinkedList<>();
        if (root == null){
            return root;
        }
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            Node pre = queue.poll();
            if (pre.left != null){
                queue.add(pre.left);
            }
            if (pre.right != null){
                queue.add(pre.right);
            }
            for (int i = 0; i < size-1; i++) {
                Node cur = queue.poll();;
                pre.next = cur;;
                pre = cur;
                if (cur.left != null){
                    queue.add(cur.left);
                }
                if (cur.right != null){
                    queue.add(cur.right);
                }
            }
        }
        return root;
    }
    /**
     * @description: 380. Insert Delete GetRandom O(1)
     * Implement the RandomizedSet class:
     *
     * RandomizedSet() Initializes the RandomizedSet object.
     * bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present,
     * false otherwise.
     * bool remove(int val) Removes an item val from the set if present. Returns true if the item was present,
     * false otherwise.
     * int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least
     * one element exists when this method is called). Each element must have the same probability of being returned.
     * @return: 实现一个随机的set集合
     * @author: kami
     * @关键词： hashset
     * @date: 2021/5/5 14:44
     */
    class RandomizedSet {
        Set<Integer> set;
        List<Object> list;
        Random rand ;
        public RandomizedSet() {
            set = new HashSet<>();
            rand = new Random();
            list= new ArrayList<>();
        }
        /**
         * Inserts a value to the set. Returns true if the set did not already contain
         * the specified element.
         */
        public boolean insert(int val) {
            if (set.contains(val)) {
                return false;
            } else {
                set.add(val);
                list.add(val);
                return true;
            }

        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified
         * element.
         */
        public boolean remove(int val) {
            if(set.contains(val)) {
                list.remove((Object) val);
                return set.remove(val);
            } else {
                return false;
            }

        }

        /** Get a random element from the set. */
        public int getRandom() {
            return (int)list.get(rand.nextInt(list.size()));
        }
    }
    /**
     * @description: 162. Find Peak Element
     * A peak element is an element that is strictly greater than its neighbors.
     *
     * Given an integer array nums, find a peak element, and return its index.
     * If the array contains multiple peaks, return the index to any of the peaks.
     * You may imagine that nums[-1] = nums[n] = -∞.
     * @return: 最高点下标
     * @author: kami
     * @关键词： 前后比较
     * @date: 2021/5/5 15:28
     */
    public int findPeakElement(int[] nums) {
        int size = nums.length;
        if (size == 1 || nums[0] > nums[1]){
            return 0;
        }
        if (nums[size-1] > nums[size-2]){
            return size-1;
        }
        for (int i = 1; i < nums.length-1;) {
            if (nums[i] > nums[i+1]){
                if (nums[i] > nums[i-1]){
                    return i;
                }
                i+=2;
            }else {
                i++;
            }
        }
        return 0;
    }
    /**
     * @description: 395. Longest Substring with At Least K Repeating Characters
     * Given a string s and an integer k, return the length of the longest substring of s such that the frequency
     * of each character in this substring is greater than or equal to k.
     * @return: 最长子串（子串每个字符出现的频率大于等于K）
     * @author: kami
     * @关键词：
     * For each h, apply two pointer technique to find the longest substring with at least K repeating characters
     * and the number of unique characters in substring is h.
     * @date: 2021/5/5 15:49
     */
    public int longestSubstring(String s, int k) {
        int length = s.length();
        if(length < k){
            return 0;
        }
        int[] ch = new int[26];
        for (char c:s.toCharArray()) {
            ch[c-'a']++;
        }
        int res = 0;
        for (int i = 0; i < length; i++) {
            char chr = s.charAt(i);
            if (ch[chr-'a']<k && ch[chr-'a'] != 0){
                String part1 = s.substring(0,i);
                String part2 = s.substring(i+1);
                res = Math.max(longestSubstring(part1,k),longestSubstring(part2,k));
                return res;
            }
        }
        return length;
    }
    /**
     * @description: 210. Course Schedule II
     * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an
     * array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want
     * to take course ai.
     *
     * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
     * Return the ordering of courses you should take to finish all courses. If there are many valid answers,
     * return any of them. If it is impossible to finish all courses, return an empty array.
     * @return: 上课的顺序
     * @author: kami
     * @关键词：
     * @date: 2021/5/7 7:37
     */
    private int N = 0;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
        Course[] courses = new Course[numCourses];
        for (int i = 0; i < numCourses; i++) {
            courses[i] = new Course(i);
        }
        for (int i = 0; i < prerequisites.length; i++) {
            courses[prerequisites[i][0]].add(courses[prerequisites[i][1]]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (isCyclic(courses[i], result)) {
                return new int[0];
            }
        }
        return result;
    }

    private boolean isCyclic(Course cur, int[] result) {
        if (cur.tested) return false;
        if (cur.visited) return true;
        cur.visited = true;
        for (Course c : cur.pre) {
            if (isCyclic(c, result)) {
                return true;
            }
        }
        cur.tested = true;
        result[N++] = cur.number;
        return false;
    }

    class Course {
        boolean visited = false;
        boolean tested = false;
        int number;
        List<Course> pre = new ArrayList<>();
        public Course(int i) {
            number = i;
        }
        public void add(Course c) {
            pre.add(c);
        }
    }
    /**
     * @description: 134. Gas Station
     * There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
     * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its
     * next (i + 1)th station. You begin the journey with an empty tank at one of the gas stations.
     * Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the
     * circuit once in the clockwise direction, otherwise return -1. If there exists a solution, it is guaranteed
     * to be unique
     * @return: 从哪个点开始能够走完一圈
     * @author: kami
     * @关键词： 如果从A到不了B，那么AB之间的任何点都到不了B；如果gas的总量大于cost的总量，那么肯定会有一个解
     * @date: 2021/5/9 18:54
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start=0,total=0,tank=0;
        for (int i = 0; i < gas.length; i++) {
            // 当前汽车剩余汽油量=从0点开始，到当前点剩余的汽油
            if ((tank = tank+gas[i]-cost[i]) < 0){
                start=i+1;
                total += tank;
                tank = 0;
            }
        }
        // total代表缺油的总量，tank代表汽车剩余的油量
        return (total+tank<0)?-1:start;
    }

    public static void main(String[] args) {
        int[] nums1 = {0};
        int i = fourSumCount(nums1, nums1, nums1, nums1);
        System.out.println(i);
    }
}

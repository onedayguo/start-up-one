package leetcode.interest;

import java.util.*;

/**
 * @Description: LeetCode30天挑战赛，每天一道题，UTC-8零点开始
 * @Auther: kami
 * @Date: 2020/4/2 22:44
 * @Version: 1.0.0
 */
public class LeetCodeCompetition {
    /**
     * @description: Happy Number
     * Write an algorithm to determine if a number is "happy".
     * A happy number is a number defined by the following process: Starting with any positive integer, replace the number
     * by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or
     * it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
     * @return:
     * @auther: kami
     * @date: 2020/4/3 8:03
     */
    public static boolean isHappy(int n) {
        boolean notHappy = true;
        char[] nChar = String.valueOf(n).toCharArray();
        Set<Integer> set = new HashSet<>();
        while (notHappy){
            int sum = 0;
            for (char i:nChar) {
                sum += Math.pow(i-'0',2);
            }
            if (sum == 1) return true;
            if (set.contains(sum)){
                return false;
            }else {
                set.add(sum);
            }
            nChar = String.valueOf(sum).toCharArray();
        }
        return false;
    }

    /**
     * @description:  Single Number
     * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
     * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
     * @return:
     * @auther: kami
     * @date: 2020/4/2 22:48
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i:nums) {
            res ^= i;
        }
        return res;
    }

    public int singleNumber1(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[0] ^= nums[i];
        }
        return nums[0];
    }

    /**
     * @description: Maximum Subarray
     * Given an integer array nums, find the contiguous subarray (containing at least one number)
     * which has the largest sum and return its sum.
     * If you have figured out the O(n) solution, try coding another solution using the divide and conquer
     * approach, which is more subtle.
     * @return: 子序列最大和
     * @auther: kami
     * @date: 2020/4/5 17:13
     */
    public int maxSubArrayBase(int[] nums) {
        int sum = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int temp = 0;
            for (int j = i; j < nums.length; j++) {
                temp += nums[j];
                sum = Math.max(temp,sum);
            }
        }
        return sum;
    }

    /**
     * @description: 动态规划版
     * @return:
     * @auther: kami
     * @date: 2020/4/5 18:00
     */
    public int maxSubArrayMid(int[] nums) {
        int maxsum, maxhere;
        maxsum = maxhere = nums[0];   //初始化最大和为a【0】
        for (int i=1; i< nums.length; i++) {
            if (maxhere <= 0)
                maxhere = nums[i];  //如果前面位置最大连续子序列和小于等于0，则以当前位置i结尾的最大连续子序列和为a[i]
            else
                maxhere += nums[i]; //如果前面位置最大连续子序列和大于0，则以当前位置i结尾的最大连续子序列和为它们两者之和
            if (maxhere > maxsum) {
                maxsum = maxhere;  //更新最大连续子序列和
            }
        }
        return maxsum;
    }

    /**
     * @description: Move Zeroes
     * iven an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
     * You must do this in-place without making a copy of the array.
     * Minimize the total number of operations.
     * @return: 把0移动到尾部的数组
     * @auther: kami
     * @date: 2020/4/5 21:54
     */
    public static void moveZeroes(int[] nums) {
        for (int i = 0,j = 0; i < nums.length; ++i) {
            if (nums[i] != 0){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j++] = temp;
            }
        }
    }

    /**
     * @description: Best Time to Buy and Sell Stock II
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one
     * and sell one share of the stock multiple times).
     * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
     * @return: 最大利润
     * @auther: kami
     * @date: 2020/4/6 11:40
     */
    public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 0; i < prices.length-1; i++) {
            if (prices[i] < prices[i+1]){
                res += (prices[i+1]-prices[i]);
            }
        }
        return res;
    }

    /**
     * @description:  Group Anagrams
     * Given an array of strings, group anagrams together.
     * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * Output:
     * [
     *   ["ate","eat","tea"],
     *   ["nat","tan"],
     *   ["bat"]
     * ]
     * @return:
     * @auther: kami
     * @date: 2020/4/9 21:51
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for (String s:strs) {
            int[] arr = new int[26];
            for (int i = 0,len=s.length(); i < len; i++) {
                arr[s.charAt(i)-'a']++;
            }
            String strKey = Arrays.toString(arr);
            List<String> tempList = map.getOrDefault(strKey, new LinkedList<>());
            tempList.add(s);
            map.put(strKey,tempList);
        }
        return new LinkedList<>(map.values());
    }

    /**
     * @description: Counting Elements
     * Given an integer array arr, count element x such that x + 1 is also in arr.
     * If there're duplicates in arr, count them seperately.
     * @return:
     * @auther: kami
     * @date: 2020/4/11 23:40
     */
    public static int countElements(int[] arr) {
        int count = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i:arr) {
            if (map.containsKey(i)){
                map.put(i,map.get(i)+1);
            }else {
                map.put(i,1);
            }
        }
        for (Map.Entry<Integer, Integer> item:map.entrySet()) {
            if (map.containsKey(item.getKey()+1)){
                count += item.getValue();
            }
        }
        return count;
    }

    static class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
    }
    /**
     * @description:  Middle of the Linked List 双指针--快慢指针
     * Given a non-empty, singly linked list with head node head, return a middle node of linked list.
     * If there are two middle nodes, return the second middle node.
     * @return:
     * @auther: kami
     * @date: 2020/4/12 10:55
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode quick = head;
        while (quick.next != null && quick.next.next!= null){
            slow = slow.next;
            quick = quick.next.next;
        }
        if (quick.next != null){
            slow = slow.next;
        }
        return slow;
    }

    /**
     * @description: Backspace String Compare
     * Given two strings S and T, return if they are equal when both are typed into empty text editors.
     * # means a backspace character.
     * @return: 
     * @auther: kami
     * @date: 2020/4/12 11:53
     */
    public boolean backspaceCompare(String S, String T) {
        Stack<Character> sStack = new Stack<>();
        Stack<Character> tStack = new Stack<>();
        for (int i = 0,len=S.length(); i < len; i++) {
            char ch = S.charAt(i);
            if (ch != '#'){
                sStack.push(ch);
            }else if (!sStack.isEmpty()){
                sStack.pop();
            }
        }
        for (int i = 0,len=T.length(); i < len; i++) {
            char ch = T.charAt(i);
            if (ch != '#'){
                tStack.push(ch);
            }else if (!tStack.isEmpty()){
                tStack.pop();
            }
        }
        return sStack.equals(tStack);
    }

    /**
     * @description: Min Stack
     * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
     * push(x) -- Push element x onto stack.
     * pop() -- Removes the element on top of the stack.
     * top() -- Get the top element.
     * getMin() -- Retrieve the minimum element in the stack.
     * @return:
     * @auther: kami
     * @date: 2020/4/12 12:27
     */
    class MinStack {
        Stack<Integer> stack;
        int min = Integer.MAX_VALUE;
        /** initialize your data structure here. */
        public MinStack() {
            stack = new Stack<>();
        }

        public void push(int x) {
            min = Math.min(x,min);
            stack.push(x);
        }

        public void pop() {
            stack.pop();
            min = Integer.MAX_VALUE;
            for (Integer i:stack) {
                min = Math.min(min,i);
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }

    public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
    }
    /**
     * @description: Diameter of Binary Tree
     * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree
     * is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
     * @return: 两节点最长距离
     * @auther: kami
     * @date: 2020/4/12 12:28
     */
    int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        getDepth(root);
        return diameter;
    }

    // 此函数是返回树的最大深度
    private int getDepth(TreeNode root) {
        if (root == null)
            return 0;
        int l = getDepth(root.left);
        int r = getDepth(root.right);
        diameter = Math.max(diameter, l + r);
        return Math.max(l, r) + 1;
    }


    public static void main(String[] args) {
        int[] arr = {1,2,3};
        int count = countElements(arr);
        System.out.print(count);
    }

}

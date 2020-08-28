package leetcode;

import java.util.HashSet;
import java.util.Set;

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
        ListNode slow=head,fast=head;
        Set<ListNode> slowStepHistory = new HashSet<>();
        while (fast != null && fast.next != null && fast.next.next != null){
            if (slowStepHistory.contains(fast.next)) {
                return fast.next;
            }else if (slowStepHistory.contains(fast.next.next)) {
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
            if (!canFinishThisCourse(i,prerequisites,waitingList,canFinish)) { return false; }
        }
        return true;
    }
    public boolean canFinishThisCourse(int course, int[][] prerequisites, boolean[] waitingList, boolean[] canFinish) {
        if (canFinish[course]) { return true; }
        if (waitingList[course]) { return false; } // find circle
        // dfs backtracking
        waitingList[course] = true;
        for (int[] pair : prerequisites) {
            if (pair[0] == course) {
                if (!canFinishThisCourse(pair[1],prerequisites,waitingList,canFinish)) { return false; }
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
        /** Initialize your data structure here. */
        public Trie() {
            this.set = new HashSet<>();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            set.add(word);
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            return set.contains(word);
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            for (String s:set) {
                if (s.startsWith(prefix)){
                    return true;
                }
            }
            return false;
        }
    }
}

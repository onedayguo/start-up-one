package leetcode.tree;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
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
            fast = fast.next.next;
            slowStepHistory.add(slow);
            slow = slow.next;
        }
        return null;
    }

    /**
     * @description: 152. Maximum Product Subarray
     * Given an integer array nums, find the contiguous subarray within an array (containing at least one number)
     * which has the largest product.
     * @return:
     * @author: kami
     * @date: 2020/8/4 22:30
     */
    public int maxProduct(int[] nums) {
        return 0;
    }
}

package interview;

import java.util.*;

/**
 * @Description:
 * @Author: kami
 * @Date: 2021/3/16 9:47
 */
public class TopInterview {
    /**
     * @Description: 344. Reverse String
     * Write a function that reverses a string. The input string is given as an array of characters char[].
     * <p>
     * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
     * <p>
     * You may assume all the characters consist of printable ascii characters.
     * @Param:
     * @Return:
     * @Author: kami
     * @Date: 2021/3/16 9:47
     */
    public void reverseString(char[] s) {
        for (int i = 0, half = s.length >> 1; i < half; i++) {
            char left = s[i];
            int right = s.length - 1 - i;
            s[i] = s[right];
            s[right] = left;
        }
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * @Description: 237. Delete Node in a Linked List
     * Write a function to delete a node in a singly-linked list. You will not be given access to the head of the list,
     * instead you will be given access to the node to be deleted directly.
     * <p>
     * It is guaranteed that the node to be deleted is not a tail node in the list.
     * @Param:
     * @Return:
     * @Author: kami
     * @Date: 2021/3/16 9:54
     */
    public void deleteNode(ListNode node) {
        while (node.next != null) {
            node.val = node.next.val;
            if (node.next.next == null) {
                node.next = null;
                break;
            }
            node = node.next;
        }
    }

    /**
     * @Description: 412. Fizz Buzz
     * Write a program that outputs the string representation of numbers from 1 to n.
     * <p>
     * But for multiples of three it should output “Fizz” instead of the number and for the multiples of five
     * output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.
     * @Param:
     * @Return:
     * @Author: kami
     * @Date: 2021/3/16 10:28
     */
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                res.add("FizzBuzz");
            } else if (i % 3 == 0) {
                res.add("Fizz");
            } else if (i % 5 == 0) {
                res.add("Buzz");
            } else {
                res.add(String.valueOf(i));
            }
        }
        return res;
    }

    class TreeNode {
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
     * @Description: 108. Convert Sorted Array to Binary Search Tree
     * Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced
     * binary search tree.
     *
     * A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never
     * differs by more than one.
     * @Param:
     * @Return:
     * @Author: kami
     * @Date: 2021/3/16 10:40
     */
    public TreeNode sortedArrayToBST(int[] num) {
        if (num.length == 0) {
            return null;
        }
        TreeNode head = helper(num, 0, num.length - 1);
        return head;
    }

    public TreeNode helper(int[] num, int low, int high) {
        if (low > high) { // Done
            return null;
        }
        int mid = (low + high) / 2;
        TreeNode node = new TreeNode(num[mid]);
        node.left = helper(num, low, mid - 1);
        node.right = helper(num, mid + 1, high);
        return node;
    }
    /**
     * @Description: 242. Valid Anagram
     * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
     * @Param: 字符串
     * @Return: 相同字母异变词
     * @Author: kami
     * @Date: 2021/3/16 10:52
     */
    public boolean isAnagram(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen != tLen){
            return false;
        }
        int[] res = new int[26];
        for (int i = 0; i < sLen; i++) {
            res[s.charAt(i) - 'a'] += 1;
            res[t.charAt(i) - 'a'] -= 1;
        }
        for (int i = 0; i < 26; i++) {
            if (res[i] != 0){
                return false;
            }
        }
        return true;
    }
    /**
     * @Description: 217. Contains Duplicate
     * Given an integer array nums, return true if any value appears at least twice in the array, and return false
     * if every element is distinct.
     * @Param:
     * @Return: 
     * @Author: kami
     * @Date: 2021/3/16 11:10
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }
    /**
     * @Description: 268. Missing Number
     * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range
     * that is missing from the array.
     *
     * Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
     * @Param:
     * @Return:
     * @Author: kami
     * @Date: 2021/3/16 11:17
     */
    public static int missingNumber(int[] nums) {
        int sum = (nums.length*(nums.length+1)) >> 1;
        for (int i = 0; i < nums.length; i++) {
            sum -= nums[i];
        }
        return sum;
    }


    /**
     * @Description: 191. Number of 1 Bits // you need to treat n as an unsigned value
     * Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as
     * the Hamming weight).
     *
     * Note:
     *
     * Note that in some languages, such as Java, there is no unsigned integer type. In this case, the input will be
     * given as a signed integer type. It should not affect your implementation, as the integer's internal binary
     * representation is the same, whether it is signed or unsigned.
     * In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 3,
     * the input represents the signed integer. -3.
     * @Param:
     * @Return: 
     * @Author: kami
     * @Date: 2021/3/16 16:47
     */
    public int hammingWeight(int n) {
        String s = Integer.toBinaryString(n);
        int len = s.length();
        int res = 0;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '1'){
                res++;
            }
        }
        return res;
    }
    /**
     * @Description: 387. First Unique Character in a String
     * Given a string, find the first non-repeating character in it and return its index. If it doesn't exist, return -1.
     * @Param:
     * @Return: 第一个不重复的字符的下标
     * @Author: kami
     * @Date: 2021/3/16 16:54
     */
    public static int firstUniqChar(String s) {
        int freq [] = new int[26];
        for(int i = 0; i < s.length(); i ++) {
            freq [s.charAt(i) - 'a'] ++;
        }
        for(int i = 0; i < s.length(); i ++) {
            if(freq [s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        Set<Integer> set = new HashSet<>();
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()){

        }
        return -1;

    }

    public static void main(String[] args) {
        String s = "leetcode";
        int i = firstUniqChar(s);

    }
}

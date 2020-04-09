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


    public static void main(String[] args) {
        int[] arr = {2,0,5,7,4,0,5,6,3,0,4,0,2,5,0,8,5,8,7,0,1,5};
        int[] arr1 = {0,1};
        moveZeroes(arr);
        System.out.print("end");
    }

}

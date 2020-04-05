package leetcode.interest;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: LeetCode30天挑战赛，每天一道题，UTC-8零点开始
 * @Auther: kami
 * @Date: 2020/4/2 22:44
 * @Version: 1.0.0
 */
public class LeetCodeCompetition {
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

    public static void main(String[] args) {
        System.out.print(isHappy(19));
    }
}

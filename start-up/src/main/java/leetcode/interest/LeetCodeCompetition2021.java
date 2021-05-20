package leetcode.interest;

import java.util.Arrays;

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
     *
     * In one move, you can increment or decrement an element of the array by 1.
     * @return: 需要多少步使所有数字相等
     * @author: kami
     * @关键词： 排序
     * @date: 2021/5/19 20:33
     */
    public int minMoves2(int[] nums) {
        // 此版本超时
        int min = nums[0],max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(min,nums[i]);
            max = Math.max(max,nums[i]);
        }
        int minStep = Integer.MAX_VALUE;
        for (int i = min; i <= max; i++) {
            int tempSum = 0;
            for (int j = 0; j < nums.length; j++) {
                tempSum += Math.abs(i-nums[j]);
            }
            minStep = Math.min(minStep,tempSum);
        }
        return minStep;
    }
    public int minMoves21(int[] nums) {
        Arrays.sort(nums);
        int i = 0, j = nums.length-1;
        int count = 0;
        while(i < j){
            count += nums[j]-nums[i];
            i++;
            j--;
        }
        return count;
    }
}

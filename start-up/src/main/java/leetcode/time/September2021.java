package leetcode.time;

/**
 * @Description: 九月
 * @Author: kami
 * @Date: 2021/9/1 08:32
 * @Version: 1.0.0
 */
public class September2021 {
    /**
     * @description: 153. Find Minimum in Rotated Sorted Array
     * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:
     * [4,5,6,7,0,1,2] if it was rotated 4 times.
     * [0,1,2,4,5,6,7] if it was rotated 7 times.
     * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
     * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
     * You must write an algorithm that runs in O(log n) time.
     * @return: 找到最小值
     * @author: kami
     * @关键词： 比较边界值
     * @date: 2021/9/1 8:32
     */
    public int findMin(int[] nums) {
        if (nums.length == 1){
            return nums[0];
        }
        int left = 0,right = nums.length-1;
        while (left < right){
            if (nums[left] < nums[right]){
                return nums[left];
            }
            int mid = (left+right)/2;
            if (nums[mid] > nums[right]){
                left = mid+1;
            }else {
                right = mid;
            }
        }
        return nums[left];
    }
    /**
     * @description: 137. Single Number II
     * Given an integer array nums where every element appears three times except for one, which appears
     * exactly once. Find the single element and return it.
     * You must implement a solution with a linear runtime complexity and use only constant extra space.
     * @return: 只出现一次的数字
     * @author: kami
     * @关键词： 时间O（n），空间O（1）
     * @date: 2021/9/3 8:11
     */
    public int singleNumber(int[] nums) {
        int ans = 0;
        for(int i = 0; i < 32; i++) {
            int sum = 0;
            for (int num : nums) {
                if (((num >> i) & 1) == 1) {
                    sum++;
                    sum %= 3;
                }
            }
            if(sum != 0) {
                ans |= sum << i;
            }
        }
        return ans;
    }
}

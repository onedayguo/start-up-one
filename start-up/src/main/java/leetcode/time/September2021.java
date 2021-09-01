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
}

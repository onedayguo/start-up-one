package leetcode.array.easy;

import java.util.*;

/**
 * @Description:  leetcode 数组类型题，简单级别
 * @Auther: kami
 * @Date: 2020/3/8 21:11
 * @Version: 1.0.0
 */
public class LeetCodeArrayEasyOne {
    /**
     * @description: 697. Degree of an Array
     * Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.
     * Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.
     * @return: smallest length of subarray
     * @auther: kami
     * @date: 2020/3/8 21:15
     */
    public int findShortestSubArray(int[] nums) {
        int length = nums.length;
        Map<Integer, Map<Integer,Integer>> count = new HashMap<>();//0数量，1 left,2 right

        for (int i = 0; i < length; i++) {
            Map<Integer,Integer> map = count.get(nums[i]);
            if (map != null) {
                int counter = map.get(0);
                map.put(0,counter+1); //数量+1
                map.put(2,i); //更新右指针
            }else {
                map = new HashMap<>();
                map.put(0,1); //初始化，数量
                map.put(1,i); //左指针
                map.put(2,i); //右指针
            }
            count.put(nums[i],map);
        }
        int max = 0,len = 0,res = 0;
        for (Map<Integer,Integer> map: count.values()) {
            if (map.get(0) == max) res = Math.min(map.get(2)-map.get(1),len);
            max = Math.max(map.get(0),max);
            len = Math.max(map.get(2)-map.get(1),len);
        }
        return res;
    }
}

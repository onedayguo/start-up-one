package leetcode.array.easy;

import leetcode.array.LeetCodeArrayEasy;

import java.util.*;

/**
 * @Description:  leetcode 数组类型题，简单级别
 * @Auther: kami
 * @Date: 2020/3/8 21:11
 * @Version: 1.0.0
 */
public class LeetCodeArrayEasyOne {

    public static void main(String[] args) {
        int[] array = {6,5,4,8};
        int[] res = smallerNumbersThanCurrent(array);
        for (int item: res) {
            System.out.println(item);
        }

    }
    /**
     * @description: 697. Degree of an Array
     * Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.
     * Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.
     * 思路：一：遍历数组，将元素作为key,将元素的数量，开始左指针，结束右指针作为value，键值对放入map
     *      二：遍历map,找出其中元素数量最多，右指针-左指针值最小的那个
     * @return: smallest length of subarray
     * @auther: kami
     * @date: 2020/3/8 21:15
     */
    public static int findShortestSubArray(int[] nums) {
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
        int max = 0;
        for (Map<Integer,Integer> map: count.values()){
            if (map.get(0) >= max ){
                if (map.get(0) > max){
                    max = map.get(0);
                    length = map.get(2)-map.get(1);
                }else {
                    length = Math.min(map.get(2)-map.get(1),length);
                }
            }
        }
        return length+1;
    }

    /**
     * @description: 1365. How Many Numbers Are Smaller Than the Current Number
     * Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it. That is,
     * for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].
     * @return: 比当前值小的元素的数量组成的数组
     * @auther: kami
     * @date: 2020/3/14 21:43
     */
    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int[] countArray = new int[nums.length];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] < nums[j]){
                    countArray[j] += 1;
                }else if (nums[i] > nums[j]){
                    countArray[i] += 1;
                }
            }
        }
        return countArray;
    }
}

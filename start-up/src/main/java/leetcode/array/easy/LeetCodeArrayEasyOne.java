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

    /**
     * @description: 1313. Decompress Run-Length Encoded List
     * Consider each adjacent pair of elements [freq, val] = [nums[2*i], nums[2*i+1]] (with i >= 0).  For each such pair,
     * there are freq elements with value val concatenated in a sublist. Concatenate all the sublists from left to right
     * to generate the decompressed list.
     * Return the decompressed list.
     * 2 <= nums.length <= 100
     * nums.length % 2 == 0
     * 1 <= nums[i] <= 100
     * 思路：首先计算返回数组的总长度，然后填充数组
     * @return: 合并好的数组
     * @auther: kami
     * @date: 2020/3/14 22:35
     */
    public static int[] decompressRLElist(int[] nums) {
        int sumLength = 0;
        for (int i = 0; i < nums.length; i+=2) {
            sumLength += nums[i];
        }
        int[] resArray = new int[sumLength];
        int fromIndex = 0;
        for (int i = 0; i < nums.length; i+=2) {
            int endIndex = fromIndex + nums[i];
            Arrays.fill(resArray,fromIndex,endIndex,nums[i+1]);
            fromIndex = endIndex;
        }
        return resArray;
    }

    /**
     * @description: 1295. Find Numbers with Even Number of Digits
     * Given an array nums of integers, return how many of them contain an even number of digits.
     * @return: 数组中元素位数是偶数的元素个数
     * @auther: kami
     * @date: 2020/3/14 23:17
     */
    public int findNumbers(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += String.valueOf(nums[i]).length() % 2 == 0 ? 1 : 0;
        }
        return sum;
    }

    /**
     * @description: 1266. Minimum Time Visiting All Points
     * On a plane there are n points with integer coordinates points[i] = [xi, yi]. Your task is to find the minimum time
     * in seconds to visit all points.You can move according to the next rules:
     * In one second always you can either move vertically, horizontally by one unit or diagonally (it means to move one
     * unit vertically and one unit horizontally in one second).
     * You have to visit the points in the same order as they appear in the array.
     * @return: 依次走过数组中的点所需要的总时间
     * @auther: kami
     * @date: 2020/3/14 23:25
     */
    public int minTimeToVisitAllPoints(int[][] points) {
        int row = points.length;
        int seconeds = 0;
        for (int i = 1; i < row; i++) {
            int rowDif = Math.abs(points[i][0] - points[i-1][0]);
            int colDif = Math.abs(points[i][1] - points[i-1][1]);
            if (rowDif == colDif){
                seconeds += rowDif;
            }else {
                seconeds += (Math.min(rowDif,colDif) + Math.abs(rowDif-colDif));
            }
        }
        return seconeds;
    }
    public static void main(String[] args) {
        int[] array = {6,5,4,8};
        int[] res = decompressRLElist(array);
        for (int item: res) {
            System.out.println(item);
        }

    }
}

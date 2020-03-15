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

    /**
     * @description: 1351. Count Negative Numbers in a Sorted Matrix
     * Given a m * n matrix grid which is sorted in non-increasing order both row-wise and column-wise.
     * Return the number of negative numbers in grid.
     * 思路：因为列是有序的，所以按照按照列遍历，发现负数计算数量，退出进行下一列；
     * 注意：遍历时，行列式互换的；
     * 优化：因为列是有序的，对列的遍历可以进行二分查找
     * @return: 数组中负数的个数
     * @auther: kami
     * @date: 2020/3/15 10:31
     */
    public int countNegatives(int[][] grid) {
        int nonNegative = 0;
        int rowLen = grid[0].length,colLen = grid.length;
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (grid[j][i] < 0){
                    nonNegative += (colLen-j);
                    break;
                }
            }
        }
        return nonNegative;
    }

    /**
     * @description: 1299. Replace Elements with Greatest Element on Right Side
     * Given an array arr, replace every element in that array with the greatest element among the elements to its right,
     * and replace the last element with -1. After doing so, return the array.
     * @return: 替换后的数组
     * @auther: kami
     * @date: 2020/3/15 12:00
     */
    public int[] replaceElements(int[] arr) {
        int lastIndex = arr.length -1;
        int maxRight = arr[lastIndex];
        int left ;
        arr[lastIndex] = -1;
        for (int i = lastIndex -1; i > -1; i--) {
            left = arr[i];
            arr[i] = maxRight;
            maxRight = Math.max(left,maxRight);
        }
        return arr;
    }

    /**
     * @description: 1252. Cells with Odd Values in a Matrix
     * Given n and m which are the dimensions of a matrix initialized by zeros and given an array indices where
     * indices[i] = [ri, ci]. For each pair of [ri, ci] you have to increment all cells in row ri and column ci by 1.
     * Return the number of cells with odd values in the matrix after applying the increment to all indices.
     * @return: 改变后的数组中的奇数的数量
     * @auther: kami
     * @date: 2020/3/15 12:11
     */
    public static int oddCells(int n, int m, int[][] indices) {
        int[] row = new int[n],col = new int[m];
        for (int i = 0; i < indices.length; i++) {
            row[indices[i][0]]++;
            col[indices[i][1]]++;
        }
        int countOdd = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int value = row[i] + col[j];
                if (value % 2 == 1){
                    countOdd++;
                }
            }
        }
        return countOdd;


    }
    public static void main(String[] args) {
        int[][] array = {{1,1},{0,0}};

        int res = oddCells(2,2,array);
        System.out.println(res);

    }
}

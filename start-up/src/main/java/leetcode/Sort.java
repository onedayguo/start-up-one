package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.TreeSet;

/**
 * @Description: 排序
 * @Author: kami
 * @Date: 2021/3/11 20:29
 * @Version: 1.0.0
 */
public class Sort {
    /**
     * @description: 1528. Shuffle String
     * Given a string s and an integer array indices of the same length.
     * <p>
     * The string s will be shuffled such that the character at the ith position moves to indices[i] in the shuffled string.
     * <p>
     * Return the shuffled string.
     * @return: 被排序的字符串
     * @author: kami
     * @备注：
     * @date: 2021/3/11 20:29
     */
    public static String restoreString(String s, int[] indices) {
        char[] ch = new char[indices.length];
        for (int i = 0; i < indices.length; i++) {
            ch[indices[i]] = s.charAt(i);
        }
        return new String(ch);
    }

    /**
     * @description: 1370. Increasing Decreasing String
     * Given a string s. You should re-order the string using the following algorithm:
     * <p>
     * Pick the smallest character from s and append it to the result.
     * Pick the smallest character from s which is greater than the last appended character to the result and append it.
     * Repeat step 2 until you cannot pick more characters.
     * Pick the largest character from s and append it to the result.
     * Pick the largest character from s which is smaller than the last appended character to the result and append it.
     * Repeat step 5 until you cannot pick more characters.
     * Repeat the steps from 1 to 6 until you pick all characters from s.
     * In each step, If the smallest or the largest character appears more than once you can choose any occurrence and
     * append it to the result.
     * <p>
     * Return the result string after sorting s with this algorithm.
     * @return: 根据规则排序后的字符串
     * @author: kami
     * @备注： 理解规则的含义
     * @date: 2021/3/11 20:57
     */
    public static String sortString(String s) {
        int len = s.length();
        int[] freq = new int[26];
        for (int index = 0; index < len; index++) {
            freq[s.charAt(index) - 'a']++;
        }
        StringBuilder sb = new StringBuilder(len);
        int count = 0;
        while (count < len) {
            // sorting up
            for (int i = 0; i < 26; i++) {
                if (freq[i] > 0) {
                    sb.append((char) (i + 'a'));
                    freq[i] = freq[i] - 1;
                    count++;
                }
            }
            // sorting down
            for (int i = 25; i >= 0; i--) {
                if (freq[i] > 0) {
                    sb.append((char) (i + 'a'));
                    freq[i] = freq[i] - 1;
                    count++;
                }
            }
        }
        return sb.toString();
    }

    /**
     * @description: 1502. Can Make Arithmetic Progression From Sequence
     * Given an array of numbers arr. A sequence of numbers is called an arithmetic progression
     * if the difference between any two consecutive elements is the same.
     * <p>
     * Return true if the array can be rearranged to form an arithmetic progression, otherwise, return false.
     * @return: 能够构造等差数列
     * @author: kami
     * @备注：TODO
     * @date: 2021/3/13 10:32
     */
    public boolean canMakeArithmeticProgression(int[] arr) {
        if (arr.length == 2) {
            return true;
        }
        Arrays.sort(arr);
        boolean res = false;
        int diff = arr[1] - arr[0];
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != diff) {
                return false;
            }
            res = true;
        }
        return res;
    }

    /**
     * @description: 1403. Minimum Subsequence in Non-Increasing Order
     * Given the array nums, obtain a subsequence of the array whose sum of elements is strictly greater
     * than the sum of the non included elements in such subsequence.
     * <p>
     * If there are multiple solutions, return the subsequence with minimum size and if there still exist
     * multiple solutions, return the subsequence with the maximum total sum of all its elements. A subsequence
     * of an array can be obtained by erasing some (possibly zero) elements from the array.
     * <p>
     * Note that the solution with the given constraints is guaranteed to be unique. Also return the answer
     * sorted in non-increasing order.
     * @return: TODO
     * @author: kami
     * @备注：TODO
     * @date: 2021/3/13 10:39
     */
    public static List<Integer> minSubsequence(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        Arrays.sort(nums);
        int subSum = nums[nums.length - 1];
        List<Integer> res = new ArrayList<>();
        res.add(subSum);
        for (int i = nums.length - 2; i >= 0; i--) {
            if (subSum > sum - subSum) {
                return res;
            }
            subSum += nums[i];
            res.add(nums[i]);
        }
        return res;
    }

    /**
     * @description: 1710. Maximum Units on a Truck
     * You are assigned to put some amount of boxes onto one truck. You are given a 2D array boxTypes,
     * where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:
     * <p>
     * numberOfBoxesi is the number of boxes of type i.
     * numberOfUnitsPerBoxi is the number of units in each box of the type i.
     * You are also given an integer truckSize, which is the maximum number of boxes that can be put on the truck.
     * You can choose any boxes to put on the truck as long as the number of boxes does not exceed truckSize.
     * <p>
     * Return the maximum total number of units that can be put on the truck.
     * @return: TODO
     * @author: kami
     * @备注：TODO
     * @date: 2021/3/13 11:33
     */
    public static int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int sum = 0;
        for (int i = 0; i < boxTypes.length; i++) {
            while (truckSize > 0 && boxTypes[i][0] > 0) {
                sum += boxTypes[i][1];
                truckSize--;
                boxTypes[i][0]--;
            }
        }
        return sum;
    }
    /**
     * @description: 220. Contains Duplicate III
     * Given an integer array nums and two integers k and t, return true if there are two distinct indices i and j
     * in the array such that abs(nums[i] - nums[j]) <= t and abs(i - j) <= k.
     * @return: TODO
     * @author: kami
     * @备注：TODO
     * @date: 2021/3/13 11:55
     */
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int len = nums.length;
        if(len == 0 || k < 1 || t < 0) {
            return false;
        }
        TreeSet<Long> S = new TreeSet<>();
        for(int start = 0, end = 0; end < len; end++) {
            Long curr = (long) nums[end];
            if(S.size() >= k + 1) {
                S.remove((long) nums[start++]);
            }
            if(S.contains(curr)) {
                return true;
            }
            if(S.ceiling(curr) != null && Math.abs(S.ceiling(curr) - curr) <= t) {
                return true;
            }
            if(S.floor(curr) != null && Math.abs(S.floor(curr) - curr) <= t) {
                return true;
            }
            S.add(curr);
        }
        return false;
    }

    public static void main(String[] args) {
        int b = 2147483646;
        int a = 2147483647;
        System.out.println(Math.abs(b+a));
        int[] nums = {-2147483648,2147483647};

        containsNearbyAlmostDuplicate(nums,1,1);
    }
}

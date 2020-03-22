package leetcode.together;

/**
 * @Description: 一起刷题
 * @Auther: kami
 * @Date: 2020/3/21 20:16
 * @Version: 1.0.0
 */
public class Leetcode {

    /**
     * @description: 365. Water and Jug Problem 水壶问题
     * you are given two jugs with capacities x and y litres. There is an infinite amount of water supply available.
     * You need to determine whether it is possible to measure exactly z litres using these two jugs.
     * If z liters of water is measurable, you must have z liters of water contained within one or both buckets by the end.
     * Operations allowed:
     * Fill any of the jugs completely with water.
     * Empty any of the jugs.
     * Pour water from one jug into another till the other jug is completely full or the first jug itself is empty.
     * @return: 是否能够盛出Z升水
     * @auther: kami
     * @date: 2020/3/21 20:19
     */
    public static boolean canMeasureWater(int x, int y, int z) {
        if (x+y < z) return false;
        if (x == z || y == z || x+y == z) return true;
        return z%gcd(x,y) == 0;

    }
    public static int gcd(int a,int b){
        if (b == 0) return a;
        return gcd(b,a%b);
    }

    /**
     * @description: 88. Merge Sorted Array
     * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
     * The number of elements initialized in nums1 and nums2 are m and n respectively.
     * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
     * @return:
     * @auther: kami
     * @date: 2020/3/22 19:19
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int temp = m + n - 1;
        m--;
        n--;
        while (n >= 0){
            if (nums1[m] <= nums2[n]){
                nums1[temp] = nums2[n];
                n--;
            }else {
                nums1[temp] = nums1[m];
                m--;
            }
            temp--;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {0};
        int[] nums2 = {1};
        merge(nums1,1,nums2,1);
    }
}

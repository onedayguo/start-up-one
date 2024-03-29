package interview;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * @Description: 阿里巴巴
 * @Auther: kami
 * @Date: 2020/3/26 10:20
 * @Version: 1.0.0
 */
public class AliBaBa {




    private static void maxCount(String nHitT,String hpStr){

        String[] nHitTStrArr = nHitT.split(" ");
        int n = Integer.parseInt(nHitTStrArr[0]);
        int hit = Integer.parseInt(nHitTStrArr[1]);
        int t = Integer.parseInt(nHitTStrArr[2]);

        String[] hps = hpStr.split(" ");
        int[] hpArr = new int[n];
        for (int i = 0; i < n; i++) {
            hpArr[i] = Integer.parseInt(hps[i]);
        }
        Arrays.sort(hpArr);
        int count = 0;
        for (int i = 0; i < n && t > 0; i++) {
            while (hpArr[i] > 0 && t > 0){
                hpArr[i] -= hit;
                t--;
            }
            if (hpArr[i] <= 0) {
                count++;
            }
        }
        System.out.println(count);
    }


    /**
     * @description: 169. Majority Element
     * Given an array nums of size n, return the majority element.
     * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that
     * the majority element always exists in the array.
     * @return: 众数
     * @author: kami
     * @关键词：
     * @date: 2021/5/11 10:59
     */
    public static int majorityElement(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        int cnt = 0;
        int res = 0;
        for (Map.Entry<Integer,Integer> entry:map.entrySet()) {
            if (entry.getValue() > cnt){
                res = entry.getKey();
                cnt = entry.getValue();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {4,5,4};
        int i = majorityElement(nums);
        System.out.println(i);
    }
















    /**
     * @description: 最大子序列期望值
     * @return:
     * @auther: kami
     * @date: 2020/3/30 19:38
     */
    public static void maxSub(int n,String nums){
        String[] numsStr = nums.split(" ");
        int[] numInt = new int[n];
        for (int i = 0; i < n; i++) {
            numInt[i] = Integer.parseInt(numsStr[i]);
        }
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(numInt[i])){
                map.put(numInt[i], map.get(numInt[i]) + 1);
            }else {
                map.put(numInt[i],1);
            }

            int max = -1;
            for (int j = i+1; j < n; j++) {
                if (numInt[j] > max){
                    map.put(numInt[j], map.get(numInt[j]) + 1);
                    max = numInt[j];
                }
            }
        }
        int sum = 0;
        for (Map.Entry<Integer,Integer> map1:map.entrySet()) {
            sum += (map1.getKey() * map1.getValue());
        }

    }

    /**
     * @description: 剩余小鸡总数
     * @return:
     * @auther: kami
     * @date: 2020/3/30 19:15
     */
    public static void sumChriken(String nmk,String nums){
        String[] start = nmk.split(" ");
        int n = Integer.parseInt(start[0]);
        int m = Integer.parseInt(start[1]);
        int k = Integer.parseInt(start[2]);
        String[] numsStr = nums.split(" ");
        int[] numInt = new int[n];
        for (int i = 0; i < n; i++) {
            numInt[i] = Integer.parseInt(numsStr[i]);
        }

        for (int i = 0; i < m; i++) {
            int index = 0;
            int maxValue = 0;
            for (int j = 0; j < n; j++) {
                numInt[j] += k;
                if (numInt[j] > maxValue){
                    index = j;
                    maxValue = numInt[j];
                }
            }
            numInt[index] = maxValue / 2;
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += numInt[i];
        }
        System.out.print(sum);
    }

    /**
     * @description: 排列组合
     * 链接：https://www.nowcoder.com/discuss/389778
     * 1、从n个人中选择任意数量的人员组成一支队伍，然后从一支队伍中选出一位队长，不同的队长算不同的组合，问这样的组合的数量对10^9+7取模 。
     * 数据范围：1 <= n <= 1000000000;
     * 输入：n = 2
     * 输出：4
     * 解释,(1),(2)(1,2),(2,1)四种，括号第一个为队长
     * 思路:
     * 首先一看数据范围，应该要O(logN)级别的方法才能AC,分析问题首先应该是个排列组合问题，得到通项公式为：
     * $ res = \sum_{i=1}^ni*C_n^i res=∑
     * 思路1：可以暴力算，当然不推荐，算了也是白算
     * 思路2：动态规划，没写出来，而且也达不到O(logN)复杂度
     * 思路3：数学知识告诉我们，res的通项公式为：
     * $ n*2^{n-1} n∗2
     * n−1
     * 要求2^n - 1，O(logN)复杂度，经典的快速幂算法。
     * @return: 组合个数
     * @auther: kami
     * @date: 2020/3/26 10:21
     */
//    public static long pow(int n) {
//        if (n == 0)
//            return 1;
//        long half = pow(n / 2);
//        if (n % 2 == 0)
//            return (half * half) % mod;
//        else
//            return (half * half * 2) % mod;
//    }

}


package leetcode.hashtable;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: hashtable简单级别的LeetCode题目
 * @Auther: kami
 * @Date: 2020/3/19 08:24
 * @Version: 1.0.0
 * @Company: 威富通科技有限公司
 * @Copyright: SwiftPass Technologies Co., LTD. Rights Reserved
 */
public class HashTableEasy {
    /**
     * @description: 771. Jewels and Stones
     * You're given strings J representing the types of stones that are jewels, and S representing the stones you have.
     * Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.
     * The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive,
     * so "a" is considered a different type of stone from "A".
     * @return:
     * @auther: kami
     * @date: 2020/3/19 8:24
     */
    public int numJewelsInStones(String J, String S) {
        Set<Character> set = new HashSet<>();
        for (char a:J.toCharArray()) {
            set.add(a);
        }
        int sum = 0;
        for (char a:S.toCharArray()) {
            if (set.contains(a)){
                sum++;
            }
        }
        return sum;
    }
    public int numJewelsInStones1(String J, String S) {
        if(J.isEmpty()) return 0;
        int cnt = 0;
        for(int i = 0 ; i < S.length() ; i++) {
            if(J.indexOf(S.charAt(i)) != -1) cnt++;
        }
        return cnt;
    }

    /**
     * @description: 961. N-Repeated Element in Size 2N Array
     * In a array A of size 2N, there are N+1 unique elements, and exactly one of these elements is repeated N times
     * Return the element repeated N times.
     * @return: 
     * @auther: kami
     * @date: 2020/3/19 23:48
     */
    public int repeatedNTimes(int[] A) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < A.length; i++) {
            if (set.contains(A[i])){
                return A[i];
            }
            set.add(A[i]);
        }
        return A[0];
    }
}

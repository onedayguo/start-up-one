package leetcode;

/**
 * @Description: leetcode第6个类
 * @Auther: kami
 * @Date: 2020/5/13 16:55
 * @Version: 1.0.0
 */
public class LeetCode6 {

    /**
     * @description: 91. Decode Ways
     * A message containing letters from A-Z is being encoded to numbers using the following mapping:
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * Given a non-empty string containing only digits, determine the total number of ways to decode it.
     * @return: 解码的方式个数
     * @auther: kami
     * @date: 2020/5/13 16:56
     */

    public static int numDecodings(String s) {
        int n = s.length();
        if (n == 0) return 0;

        int[] memo = new int[n+1];
        memo[n]  = 1;
        memo[n-1] = s.charAt(n-1) != '0' ? 1 : 0;

        for (int i = n - 2; i >= 0; i--)
            if (s.charAt(i) == '0') continue;
            else memo[i] = (Integer.parseInt(s.substring(i,i+2))<=26) ? memo[i+1]+memo[i+2] : memo[i+1];

        return memo[0];
    }

    public static void main(String[] args) {
        String s="10";
        System.out.println(numDecodings(s));
    }

}

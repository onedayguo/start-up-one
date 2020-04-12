package leetcode;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Description: LeetCode最受欢迎100题
 * @Auther: kami
 * @Date: 2020/4/12 16:08
 * @Version: 1.0.0
 */
public class LeetCodeTop100 {
    /**
     * @description: 139. Word Break 中等难度
     * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
     * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
     * Note:
     * The same word in the dictionary may be reused multiple times in the segmentation.
     * You may assume the dictionary does not contain duplicate words.
     * Input: s = "leetcode", wordDict = ["leet", "code"]
     * Output: true
     * Explanation: Return true because "leetcode" can be segmented as "leet code".
     * @return:
     * @auther: kami
     * @date: 2020/4/12 16:09
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        //此方法时间超过限制
        int size = wordDict.size();
        return helperWordBreak(s,wordDict,size);
    }
    private static boolean helperWordBreak(String newStr,List<String> wordDict,int size){
        if (newStr == null || newStr.length() == 0) return true;
        for (int i = 0; i < size; i++) {
            boolean flag = newStr.startsWith(wordDict.get(i));
            if (flag){
                int iSize = wordDict.get(i).length();
                String nextStr = newStr.substring(iSize);
                if (helperWordBreak(nextStr,wordDict,size)){
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * @description: 动态规划
     * @return:
     * @auther: kami
     * @date: 2020/4/12 16:42
     */
    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0) return false;

        int n = s.length();

        // dp[i] represents whether s[0...i] can be formed by dict
        boolean[] dp = new boolean[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                String sub = s.substring(j, i + 1);

                if (dict.contains(sub) && (j == 0 || dp[j - 1])) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        String s = "cars";
        List<String> list = new ArrayList<>();
        list.add("car");
        list.add("ca");
        list.add("rs");
        System.out.println(wordBreak(s,list));
    }
}

package leetcode;

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
     *
     * The string s will be shuffled such that the character at the ith position moves to indices[i] in the shuffled string.
     *
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
     *
     * Pick the smallest character from s and append it to the result.
     * Pick the smallest character from s which is greater than the last appended character to the result and append it.
     * Repeat step 2 until you cannot pick more characters.
     * Pick the largest character from s and append it to the result.
     * Pick the largest character from s which is smaller than the last appended character to the result and append it.
     * Repeat step 5 until you cannot pick more characters.
     * Repeat the steps from 1 to 6 until you pick all characters from s.
     * In each step, If the smallest or the largest character appears more than once you can choose any occurrence and
     * append it to the result.
     *
     * Return the result string after sorting s with this algorithm.
     * @return: 根据规则排序后的字符串
     * @author: kami
     * @备注： 理解规则的含义
     * @date: 2021/3/11 20:57
     */
    public String sortString(String s) {
        StringBuilder res = new StringBuilder();
        return "";
    }
    public static void main(String[] args) {
        String s = "codeleet";
        int[] indices = {4,5,6,7,0,2,1,3};
        restoreString(s,indices);
    }
}

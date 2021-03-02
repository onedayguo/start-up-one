package leetcode;

import java.util.List;

/**
 * @Description:
 * @Author: kami
 * @Date: 2021/3/1 11:22
 */
public class LeetcodeString {
    /**
     * @Description: 125. Valid Palindrome
     * Given a string s, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
     * @Author: kami
     * @Date: 2021/3/1 11:23
     */
    public boolean isPalindrome(String s) {
        int n = s.length()-1;
        int start = 0;
        while (start < n){
            char left = s.charAt(start);
            boolean startIsAlphanumeric = isNumOrChar(left);
            if (!startIsAlphanumeric){
                start++;
                continue;
            }
            char right = s.charAt(n);
            boolean nIsAlphanumeric = isNumOrChar(right);
            if (!nIsAlphanumeric){
                n--;
                continue;
            }
            if (left != right && Character.toUpperCase(left) != Character.toUpperCase(right)){
                return false;
            }else {
                start++;
                n--;
            }

        }
        return true;
    }
    private  boolean isNumOrChar(char a){
        return (a >= 'a' && a <= 'z') || (a >= 'A' && a <= 'Z') || (a >= '0' && a <= '9');
    }

    /**
     * @Description: 1773. Count Items Matching a Rule
     * You are given an array items, where each items[i] = [typei, colori, namei] describes the type, color, and name
     * of the ith item. You are also given a rule represented by two strings, ruleKey and ruleValue.
     *
     * The ith item is said to match the rule if one of the following is true:
     *
     * ruleKey == "type" and ruleValue == typei.
     * ruleKey == "color" and ruleValue == colori.
     * ruleKey == "name" and ruleValue == namei.
     * Return the number of items that match the given rule.
     * @Param:
     * @Return: 
     * @Author: kami
     * @Date: 2021/3/2 21:37
     */
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int th = 0;
        if ("color".equals(ruleKey)){
            th = 1;
        }else if ("name".equals(ruleKey)){
            th = 2;
        }
        int finalTh = th;
        int count = 0;
        return (int)items.stream().filter(e -> ruleValue.equals(e.get(finalTh))).count();

    }
}

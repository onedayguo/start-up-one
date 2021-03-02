package leetcode;

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
}

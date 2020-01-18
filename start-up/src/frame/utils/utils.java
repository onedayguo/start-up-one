package frame.utils;

import java.util.*;

public class utils {
    //region 快捷键：ctrl+alt+t

    /**
     * 输入0101001字符串，输出压缩后的000 ->03  11->I2
     *
     * @param in
     * @return
     */
    public static String transfer(String in) {
        int len = in.length();
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < len; ) {
            if (i == len-1){
                resultString.append(in.charAt(i));
                break;
            }

            for (int j = i+1; j < len; j++) {
                if (j == len-1){
                    return resultString.toString();
                }
                if (in.charAt(i) != in.charAt(j)) {
                    int differ = j - i;
                    if (differ == 1){
                        resultString.append(in.charAt(i));
                    }
                    else{
                        resultString.append(in.charAt(i));
                        resultString.append(in.charAt(i));
                        resultString.append(differ);
                    }
                    i = i+ differ;
                    break;
                }

            }

        }
    return resultString.toString();
    }
        //region 判断括号是否匹配，返回布尔值；利用栈的特性，先进后出，新压入的字符判断栈top是否是对应括号
        public boolean isValid (String s){
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(' || c == '[' || c == '{')
                    stack.push(c);
                else {
                    if (stack.isEmpty())
                        return false;
                    char topChar = stack.pop();
                    if (c == ')' && topChar != '(')
                        return false;
                    if (c == ']' && topChar != '[')
                        return false;
                    if (c == '}' && topChar != '{')
                        return false;
                }
            }
            return stack.isEmpty();
        }
        //endregion



    // 判断A是否是B的子字符串
    public boolean isSubstring(String A,String B){
        if (A.isEmpty() || B.isEmpty()) return false;
        int aLen = A.length(),bLen = B.length();
        if (aLen > bLen) return false;
        for (int i = 0; i < bLen - aLen +1 ; i++) {
            int tempI = i;
            int startB = 0;
            while (B.charAt(tempI) == A.charAt(startB)){
                if (startB == bLen -1) return true;
                else {
                    tempI++;
                    startB++;
                }
            }
        }
        return false;
    }
}

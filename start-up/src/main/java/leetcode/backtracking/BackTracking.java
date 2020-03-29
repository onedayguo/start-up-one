package leetcode.backtracking;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Description: 回朔法
 * @Auther: kami
 * @Date: 2020/3/29 10:16
 * @Version: 1.0.0
 */
public class BackTracking {
    /**
     * @description: 784. Letter Case Permutation
     * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
     * Return a list of all possible strings we could create.
     * @return:
     * @auther: kami
     * @date: 2020/3/29 10:16
     */
    public List<String> letterCasePermutationBFS(String S) {
        if (S == null){
            return new LinkedList<>();
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(S);
        int length = S.length();
        for (int i = 0; i < length; i++) {
            if (Character.isLetter(S.charAt(i))){
                int size = queue.size();
                for (int j = 0; j < size; j++) {
                    char[] chars = queue.poll().toCharArray();

                    chars[i] = Character.toUpperCase(chars[i]);
                    queue.offer(String.valueOf(chars));

                    chars[i] = Character.toLowerCase(chars[i]);
                    queue.offer(String.valueOf(chars));
                }
            }
        }
        return new LinkedList<>(queue);
    }

    /**
     * @description: 784. Letter Case Permutation
     * @return:
     * @auther: kami
     * @date: 2020/3/29 10:44
     */
    public List<String> letterCasePermutationDFS(String S){
        if (S == null) {
            return new LinkedList<>();
        }

        List<String> res = new LinkedList<>();
        //借助一个帮助函数
        helper(S.toCharArray(),res,0);
        return res;
    }
    public void helper(char[] chs, List<String> res, int pos) {
        //首先判断长度是否足够，其实这个地方不用判断，直接添加也可以？
        if (pos == chs.length) {
            res.add(new String(chs));
            return;
        }
        //如果当前字符时数字，则跳到下个字符
        if (chs[pos] >= '0' && chs[pos] <= '9') {
            helper(chs, res, pos + 1);
            return;
        }

        //转化为小写，从这个入口进去的是一个以当前字母为小写的字符串的组合
        chs[pos] = Character.toLowerCase(chs[pos]);
        helper(chs, res, pos + 1);

        ////转化为大写，从这个入口进去的是一个以当前字母为大写的字符串的组合
        chs[pos] = Character.toUpperCase(chs[pos]);
        helper(chs, res, pos + 1);
    }
}

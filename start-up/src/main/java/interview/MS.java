package interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * @Description: 微软
 * @Author: kami
 * @Date: 2021/5/12 10:16
 * @Version: 1.0.0
 */
public class MS {
    /**
     * @discription Given a string s, reverse only all the vowels in the string and return it.
     * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both cases.
     * @date 2021/5/12 10:17
     **/
//    public static void main(String[] args) {
//        int[] stocks = {7,1,5,3,6,4};
//        System.out.println(getMaxStockPrice(stocks));
//        int[] stocks1 = {7,6,4,3,1};
//        System.out.println(getMaxStockPrice(stocks1));
//        int[] stocks2 = {7,1,4,3,1};
//        System.out.println(getMaxStockPrice(stocks2));
//        int[] stocks3 = {1,2,3,4,5};
//        System.out.println(getMaxStockPrice(stocks3));
//        int[] stocks4 = {1,2,0,4,5};
//        System.out.println(getMaxStockPrice(stocks4));
//        int[] stocks5 = {3};
//        System.out.println(getMaxStockPrice(stocks5));
//        int[] stocks6 = {3,3,3,3,3};
//        System.out.println(getMaxStockPrice(stocks6));
//    }

    /**
     * @description: 调换元音字母
     * @author: kami
     * @date: 2021/5/12 11:04
     */
    private static String reverseStr(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');
        char[] chars = str.toCharArray();
        int size = chars.length;
        int left = 0;
        int right = size - 1;
        while (left < right) {
            while (left < size && !set.contains(chars[left])) {
                left++;
            }
            while (right > -1 && !set.contains(chars[right])) {
                right--;
            }
            if (left < right && chars[left] != chars[right]) {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
            }
            left++;
            right--;
        }
        return Arrays.toString(chars);
    }

    /**
     * @description: 买卖一次股票赚的最多钱
     * @author: kami
     * @date: 2021/5/12 11:05
     */
    private static int getMaxStockPrice(int[] stocks) {
        if (stocks == null || stocks.length == 0) {
            return 0;
        }
        int minPrice = stocks[0];
        int maxProfit = 0;
        for (int i = 0; i < stocks.length; i++) {
            minPrice = Math.min(stocks[i], minPrice);
            maxProfit = Math.max(stocks[i] - minPrice, maxProfit);
        }
        return maxProfit;
    }

    /**
     * @description: 买卖多次股票赚的最多的前
     * @author: kami
     * @date: 2021/5/12 11:05
     */
    private static int getMaxStockPriceMany(int[] stocks) {
        int res = 0;
        for (int i = 0; i < stocks.length - 1; i++) {
            if (stocks[i] < stocks[i + 1]) {
                res += (stocks[i + 1] - stocks[i]);
            }
        }
        return res;
    }

    /**
     * @description: 求所有出栈顺序
     * @return: 所有顺序
     * @author: kami
     * @关键词： 后进先出
     * @date: 2021/5/17 19:26
     */
    List<List<Integer>> resStack = new LinkedList<>();

    private void fun(int curIndex, int[] in, Stack<Integer> stk, List<Integer> curRes) {
        // 如果入栈完毕了，且也栈空了;递归的结束条件
        if (curIndex == in.length && stk.isEmpty()) {
            resStack.add(new LinkedList<>(curRes));
        } else {
            //选择入栈
            if (curIndex < in.length) {
                Stack<Integer> s1 = (Stack<Integer>) stk.clone();
                s1.push(in[curIndex]);
                fun(curIndex + 1, in, s1, curRes);
            }
            //选择出栈
            if (!stk.isEmpty()) {
                Stack<Integer> s2 = (Stack<Integer>) stk.clone();
                List<Integer> copyCurRes = new LinkedList<>(curRes);
                copyCurRes.add(s2.pop());
                fun(curIndex, in, s2, copyCurRes);
            }
        }
    }

    static Stack<Integer> stk = new Stack<>();
    static List<List<Integer>> res = new LinkedList<>();
    static List<Integer> cur = new LinkedList<>();
    private static void df(int n,int[] nums){
        if (n == nums.length){
            Stack<Integer> clone = (Stack<Integer>)stk.clone();
            while (!clone.isEmpty()){
                cur.add(clone.pop());
            }
            res.add(new LinkedList<>(cur));
            return;
        }
        stk.push(nums[n]);
        df(n+1,nums);
        cur.add(stk.pop());
        if (!stk.isEmpty()){
            int a = stk.pop();
            cur.add(a);
            df(n,nums);
            stk.push(a);
            cur.remove(cur.size()-1);
        }
    }

    public static void main(String[] args) {
        MS ms = new MS();
        int[] in = {1,2,3};
        ms.fun(0,in,new Stack<>(),new LinkedList<>());
        ms.resStack.forEach(e->e.forEach(System.out::print));

        df(0,new int[]{1,2,3});
        System.out.println(res);
    }

    // helloworld
    // 1. matrix A[m][n], int, move from [0][0] to [m-1][n-1], you can move from 4 directions,
    // cost the absolute difference between two nodes. output the minimal cost
    // 动态规划 dp[i][j] 代表从0,0,点到当前点用到的最小花费，则dp[m][n]即为所求
    private int getMinCost(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + Math.abs(matrix[0][i] - matrix[0][i - 1]);
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + Math.abs(matrix[i][0] - matrix[i - 1][0]);
        }

        for (int i = 1; i < m; m++) {
            for (int j = 1; j < n; j++) {
                int leftToRightCost = dp[i][j - 1] + Math.abs(matrix[i][j] - matrix[i][j - 1]);
                int rightToLeftCost = 0;
                int upToDownCost = dp[i - 1][j] + Math.abs(matrix[i][j] - matrix[i - 1][j]);
                dp[i][j] = Math.min(leftToRightCost, upToDownCost);
            }
        }
        return dp[m - 1][n - 1];
    }
}


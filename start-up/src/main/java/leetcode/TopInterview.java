package leetcode;

import java.util.Arrays;

/**
 * @Description: LeetCode面试最多,由简单到中等再到困难
 * @Author: kami
 * @Date: 2021/4/26 11:37
 * @Version: 1.0.0
 */
public class TopInterview {
    /**
     * @description: 190. Reverse Bits
     * Reverse bits of a given 32 bits unsigned integer.
     * Note:
     * Note that in some languages such as Java, there is no unsigned integer type. In this case,
     * both input and output will be given as a signed integer type. They should not affect your implementation,
     * as the integer's internal binary representation is the same, whether it is signed or unsigned.
     * In Java, the compiler represents the signed integers using 2's complement notation. Therefore,
     * in Example 2 above, the input represents the signed integer -3 and the output represents the signed
     * integer -1073741825.
     * Follow up:
     * If this function is called many times, how would you optimize it?
     * @return: 翻转比特后的数字
     * @author: kami
     * @关键词： 移位
     * @date: 2021/4/26 11:38
     */
    public int reverseBits(int n) {
        int res=0;
        for(int i=0;i<32;i++){
            // 左移扩大
            res = ( res << 1 ) | ( n & 1 );
            n = n >> 1;
        }
        return res;
    }
    /**
     * @description: 326. Power of Three
     * Given an integer n, return true if it is a power of three. Otherwise, return false.
     * An integer n is a power of three, if there exists an integer x such that n == 3x.
     * @return: n是否是3的幂次
     * @author: kami
     * @关键词： 循环除以3
     * @date: 2021/4/26 12:37
     */
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        while (n > 1){
            if (n%3 != 0){
                return false;
            }
            n /= 3;
        }
        return true;
    }
    /**
     * @description: 172. Factorial Trailing Zeroes
     * Given an integer n, return the number of trailing zeroes in n!.
     * Follow up: Could you write a solution that works in logarithmic time complexity?
     * @return: 阶乘值0的个数
     * @author: kami
     * @关键词： 数一下5的个数
     * @date: 2021/4/26 12:49
     */
    public static int trailingZeroes(int n) {
        int cntFive = 0;
        int cur = 5;
        while (cur <= n){
            int value = cur;
            while (value % 5 == 0){
                cntFive++;
                value /= 5;
            }
            cur += 5;
        }
        return cntFive;
    }

    public static int trailingZeros1(int n){
        return n == 0 ? 0 : n / 5 + trailingZeros1(n / 5);
    }
    /**
     * @description: 204. Count Primes
     * Count the number of prime numbers less than a non-negative number, n.
     * @return: 小于N的质数的个数
     * @author: kami
     * @关键词：
     * @date: 2021/4/27 11:22
     */
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (!isPrime[i]){
                cnt++;
                for (int j = 2*i; j < n; j+=i) {
                    isPrime[j] = true;
                }
            }
        }
        return cnt;
    }
    /**
     * @description: 289. Game of Life
     * According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised
     * by the British mathematician John Horton Conway in 1970."
     *
     * The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1)
     * or dead (represented by a 0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal)
     * using the following four rules (taken from the above Wikipedia article):
     * Any live cell with fewer than two live neighbors dies as if caused by under-population.
     * Any live cell with two or three live neighbors lives on to the next generation.
     * Any live cell with more than three live neighbors dies, as if by over-population.
     * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
     * The next state is created by applying the above rules simultaneously to every cell in the current state,
     * where births and deaths occur simultaneously. Given the current state of the m x n grid board, return the
     * next state.
     * @return: 下一代的状态数组
     * @author: kami
     * @关键词： 开辟新数组记录下一代状态
     * @date: 2021/4/27 12:25
     */
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length, n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lives = liveNeighbors(board, m, n, i, j);

                // In the beginning, every 2nd bit is 0;
                // So we only need to care about when will the 2nd bit become 1.
                if (board[i][j] == 1 && lives >= 2 && lives <= 3) {
                    board[i][j] = 3; // Make the 2nd bit 1: 01 ---> 11
                }
                if (board[i][j] == 0 && lives == 3) {
                    board[i][j] = 2; // Make the 2nd bit 1: 00 ---> 10
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;  // Get the 2nd state.
            }
        }
    }

    public int liveNeighbors(int[][] board, int m, int n, int i, int j) {
        int lives = 0;
        for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, m - 1); x++) {
            for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n - 1); y++) {
                lives += board[x][y] & 1;
            }
        }
        lives -= board[i][j] & 1;
        return lives;
    }
    /**
     * @description: 415. Add Strings
     * Given two non-negative integers, num1 and num2 represented as string,
     * return the sum of num1 and num2 as a string.
     * Input: num1 = "11", num2 = "123"
     * Output: "134"
     * @return: 字符串数字相加
     * @author: kami
     * @关键词： 累加，进位
     * @date: 2021/4/28 21:41
     */
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        // 计算完以后的答案需要翻转过来
        ans.reverse();
        return ans.toString();
    }

    public static void main(String[] args) {
        TopInterview top = new TopInterview();
        String s = top.addStrings("11", "123");
        System.out.println(s);
    }
}
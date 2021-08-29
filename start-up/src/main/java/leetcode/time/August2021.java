package leetcode.time;

import java.util.Arrays;

/**
 * @Description: 8月份刷题
 * @Author: kami
 * @Date: 2021/8/28 12:28
 * @Version: 1.0.0
 */
public class August2021 {
    /**
     * @description: 130. Surrounded Regions
     * Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally
     * surrounded by 'X'.
     * A region is captured by flipping all 'O's into 'X's in that surrounded region.
     * @return: 改变0后的数组
     * @author: kami
     * @关键词：
     * @date: 2021/8/28 12:31
     */
    private void DFS(char[][] board, int r, int c, int rsize, int csize) {
        if (r < 0 || c < 0 || r == rsize || c == csize || board[r][c] != 'O') {
            return;
        }
        board[r][c] = 'P';
        DFS(board, r + 1, c, rsize, csize);
        DFS(board, r, c + 1, rsize, csize);
        DFS(board, r - 1, c, rsize, csize);
        DFS(board, r, c - 1, rsize, csize);
    }

    public void solve(char[][] board) {
        if (board.length == 0) {
            return;
        }
        int row = board.length, col = board[0].length;

        for (int i = 0; i < col; i++) {
            DFS(board, 0, i, row, col);//for FIRST ROW
            DFS(board, row - 1, i, row, col);//for LAST ROW
        }

        for (int i = 0; i < row; i++) {
            DFS(board, i, 0, row, col);//for FIRST COLUMN
            DFS(board, i, col - 1, row, col);//for LAST COLUMN
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'P') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    /**
     * @description: 135. Candy
     * There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
     *
     * You are giving candies to these children subjected to the following requirements:
     *
     * Each child must have at least one candy.
     * Children with a higher rating get more candies than their neighbors.
     * Return the minimum number of candies you need to have to distribute the candies to the children.
     * @return: 最少需要的糖果数量
     * @author: kami
     * @关键词： 记录前两个值
     * @date: 2021/8/29 8:59
     */
    public int candy(int[] ratings) {
        int size=ratings.length;
        if(size<=1) {
            return size;
        }
        int[] num = new int[size];
        Arrays.fill(num,1);
        for (int i = 1; i < size; i++)
        {
            if(ratings[i]>ratings[i-1]) {
                num[i]=num[i-1]+1;
            }
        }
        for (int i= size-1; i>0 ; i--)
        {
            if(ratings[i-1]>ratings[i]) {
                num[i-1]=Math.max(num[i]+1,num[i-1]);
            }
        }
        int result=0;
        for (int i = 0; i < size; i++)
        {
            result+=num[i];
        }
        return result;
    }
}

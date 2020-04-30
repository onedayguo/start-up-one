package leetcode.interest;

import java.util.List;

/**
 * @Description: This is the BinaryMatrix's API interface.
 * You should not implement it, or speculate about its implementation
 * @Auther: kami
 * @Date: 2020/4/29 20:48
 * @Version: 1.0.0
 */
public interface BinaryMatrix {
    int get(int row, int col);
    List<Integer> dimensions();
}

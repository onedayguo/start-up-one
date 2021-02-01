package leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: leetcode 数组类型题，简单级别
 * @Auther: kami
 * @Date: 2020/3/8 21:09
 * @Version: 1.0.0
 */
public class LeetCodeArrayEasy {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * @Description: 118. Pascal's Triangle
     * @Param:
     * @Return:
     * @Author: kami
     * @Date: 2020/12/26 18:28
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> allrows = new ArrayList<>();
        ArrayList<Integer> row = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            row.add(0, 1);
            for (int j = 1; j < row.size() - 1; j++) {
                row.set(j, row.get(j) + row.get(j + 1));
            }
            allrows.add(new ArrayList<>(row));
        }
        return allrows;
    }
    /**
     * @description: 119. Pascal's Triangle II
     * Given an integer rowIndex, return the rowIndexth row of the Pascal's triangle.
     *
     * Notice that the row index starts from 0.
     * Could you optimize your algorithm to use only O(k) extra space?
     * @return: 第rowIndex的數字列表
     * @author: kami
     * @date: 2021/2/1 20:35
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>(rowIndex+1);
        res.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i-1; j >=1 ; j--) {
                int temp = res.get(j-1)+res.get(j);
                res.set(j,temp);
            }
            res.add(1);
        }
        return res;
    }

    /**
     * @Description: 106. Construct Binary Tree from Inorder and Postorder Traversal 从中序遍历和后序遍历重构二叉树
     * @Author: kami
     * @Date: 2020/12/26 18:39
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return new TreeNode().left;
    }

    /**
     * @description: 1480. Running Sum of 1d Array
     * Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
     * Return the running sum of nums.
     * @return:
     * @author: kami
     * @date: 2021/1/11 23:22
     */
    public int[] runningSum(int[] nums) {
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i-1]+ nums[i];
        }
        return sum;
    }

    public String defangIPaddr(String address) {
        return address.replace(".","[.]");
    }
}

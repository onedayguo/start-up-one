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
     * @Description: 106. Construct Binary Tree from Inorder and Postorder Traversal 从中序遍历和后序遍历重构二叉树
     * @Param:
     * @Return: 
     * @Author: kami
     * @Date: 2020/12/26 18:39
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return new TreeNode().left;
    }
}

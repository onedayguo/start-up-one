package leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 树形结构
 * @Auther: kami
 * @Date: 2020/4/6 11:55
 * @Version: 1.0.0
 */
public class Tree {
     class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * @description: 938. Range Sum of BST
     * Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).
     * The binary search tree is guaranteed to have unique values.
     * @return:
     * @auther: kami
     * @date: 2020/4/6 11:56
     */
    int sumTree ;
    public int rangeSumBST(TreeNode root, int L, int R) {
        sumTree = 0;
        helperSum(root,L,R);
        return sumTree;
    }
    private void helperSum(TreeNode childNode,int min,int max){
        if (childNode != null){
            if(childNode.val >= min && childNode.val<= max) {
                sumTree += childNode.val;
            }
            if (childNode.val > min){
                helperSum(childNode.left,min,max);
            }
            if (childNode.val < max){
                helperSum(childNode.right,min,max);
            }
        }
    }
    /**
     * @Description: 103. Binary Tree Zigzag Level Order Traversal
     * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
     * (i.e., from left to right, then right to left for the next level and alternate between).
     * @Param:
     * @Return: 
     * @Author: kami
     * @Date: 2021/3/2 21:52
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        return new ArrayList<>();
    }
}

package leetcode.binary;

/**
 * @Description: binary search简单级别的LeetCode题目
 * @Auther: kami
 * @Date: 2020/3/20 00:04
 * @Version: 1.0.0
 * @Company: 威富通科技有限公司
 * @Copyright: SwiftPass Technologies Co., LTD. Rights Reserved
 */
public class BinarySearchEasy {
    /**
     * @description: 1207. Unique Number of Occurrences
     * Given an array of integers arr, write a function that returns true if and only if the number of
     * occurrences of each value in the array is unique.
     * @return: 
     * @auther: kami
     * @date: 2020/3/20 0:05
     */
    public boolean uniqueOccurrences(int[] arr) {
        return false;
    }

    /**
     * @description: 110. Balanced Binary Tree
     * Given a binary tree, determine if it is height-balanced
     * For this problem, a height-balanced binary tree is defined as:
     * a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
     * 衡二叉树的判定，一般来说可以在TreeNode数据结构中增加一个depth表示以该节点为根节点的子树的深度，从而利用左右儿子节点子树深度的差值做判断。
     * @return:
     * @auther: kami
     * @date: 2020/3/21 9:00
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int left = dfs(root.left);
        int right = dfs(root.right);
        return Math.abs(left-right)<=1 && isBalanced(root.left) && isBalanced(root.right);
    }
    public int dfs(TreeNode node){
        if (node == null) return 0;
        //利用dfs实现子树深度的求取 ，父节点的depth为左右儿子节点max值+1。
        int left = dfs(node.left) + 1;
        int right = dfs(node.right) + 1;
        return  Math.max(left,right);
    }
    class TreeNode {
         int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

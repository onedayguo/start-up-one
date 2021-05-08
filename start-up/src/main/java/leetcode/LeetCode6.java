package leetcode;

/**
 * @Description: leetcode第6个类
 * @Auther: kami
 * @Date: 2020/5/13 16:55
 * @Version: 1.0.0
 */
public class LeetCode6 {

    /**
     * @description: 91. Decode Ways
     * A message containing letters from A-Z is being encoded to numbers using the following mapping:
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * Given a non-empty string containing only digits, determine the total number of ways to decode it.
     * @return: 解码的方式个数
     * @auther: kami
     * @date: 2020/5/13 16:56
     */
    public static int numDecodings(String s) {
        int n = s.length();
        if (n == 0) return 0;

        int[] memo = new int[n + 1];//初始化元素默认0
        memo[n] = 1; //额外的计数设置为1，保证字符串至少有一种解码方式
        memo[n - 1] = s.charAt(n - 1) == '0' ? 0 : 1; //

        for (int i = n - 2; i >= 0; i--) {
            if (s.charAt(i) != '0') {
                //动态规划思想；如果从当前位置向后截取两位，值小于等于26的话，则需累积上一步的基数，否则与上一步保持一致
                memo[i] = (Integer.parseInt(s.substring(i, i + 2)) <= 26) ? memo[i + 1] + memo[i + 2] : memo[i + 1];
            }
        }

        return memo[0];
    }

    public class TreeNode {
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
     * @description: 99. Recover Binary Search Tree
     * You are given the root of a binary search tree (BST), where exactly two nodes of the tree were
     * swapped by mistake. Recover the tree without changing its structure.
     * Follow up: A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
     * @return:
     * @author: kami
     * @关键词： 中序遍历，左中右
     * @date: 2021/5/8 12:16
     */
    private TreeNode first;
    private TreeNode second;
    private TreeNode pre;
    public void recoverTree(TreeNode root) {
        if(root==null) {
            return;
        }
        first = null;
        second = null;
        pre = null;
        inorder(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void inorder(TreeNode root){
        if(root==null) {
            return;
        }
        inorder(root.left);

        if(first==null && (pre==null ||pre.val>=root.val)){
            first = pre;
        }
        if(first!=null && pre.val>=root.val){
            second = root;
        }
        pre = root;
        inorder(root.right);
    }

    public static void main(String[] args) {
        String s = "10";
        System.out.println(numDecodings(s));
    }

}

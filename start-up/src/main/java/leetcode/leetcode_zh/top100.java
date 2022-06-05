package leetcode.leetcode_zh;


public class top100 {
    long pre = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 访问左子树
        if (!isValidBST(root.left)) {
            return false;
        }
        // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        // 访问右子树
        return isValidBST(root.right);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1, new TreeNode(1), null);
        top100 tree = new top100();
        boolean is = tree.isValidBST(node);
        System.out.println(is);
    }
}



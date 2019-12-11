package advance.binarySearch;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Test {
    //前序遍历递归法
    public void preOrderRecursive(BinaryTreeNode root) {
        if (root != null) {
            System.out.println(root.getData() + '\t');
            preOrderRecursive(root.getLeft());//递归遍历左子树
            preOrderRecursive(root.getRight());//递归遍历右子树
        }
    }

    //前序遍历非递归法
    public void preOrder(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        while (true) {
            while (root != null) {
                System.out.println(root.getData() + '\t');
                stack.push(root);
                root = root.getLeft();
            }
            if (stack.isEmpty()) break;
            root = stack.pop();
            root = root.getRight();
        }


    }

    //中序遍历递归法
    public void midOrderRecursive(BinaryTreeNode root){
        if (root != null){
            midOrderRecursive(root.getLeft());
            System.out.println(root.getData()+'\t');
            midOrderRecursive(root.getRight());
        }
    }
    //中序遍历非递归法
    public void midOrder(BinaryTreeNode root){
        Stack<BinaryTreeNode> stack = new Stack<>();
        while (true){
            while (root != null){
                stack.push(root);
                root = root.getLeft();
            }
            if (stack.isEmpty()) break;
            root = stack.pop();
            System.out.println(root.getData()+'\t');
            root = root.getRight();

        }
    }

    //后序遍历递归法
    public void backOrderRecursive(BinaryTreeNode root){
        if (root != null) {
            backOrderRecursive(root.getLeft());
            backOrderRecursive(root.getRight());
            System.out.println(root.getData()+'\t');
        }
    }
    //后序遍历非递归法
    public void backOrder(BinaryTreeNode root){
        Stack<BinaryTreeNode> stack = new Stack<>();

    }

    //层序 遍历
    public void levelSee(BinaryTreeNode root){
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        BinaryTreeNode temp;
        queue.offer(root);
        while (! queue.isEmpty()){
            temp = queue.poll();
            System.out.println(temp.getData()+'\t');
            if (temp.getLeft() != null) {
                queue.offer(temp.getLeft());
            }
            if (temp.getRight() != null) {
                queue.offer(temp.getRight());
            }
        }
    }








}
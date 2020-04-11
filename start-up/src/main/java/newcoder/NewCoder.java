package newcoder;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @Description: 牛客剑指offer
 * @Auther: kami
 * @Date: 2020/4/11 22:42
 * @Version: 1.0.0
 */
public class NewCoder {
    /**
     * @description: 数组
     * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * 左 - 右 大
     * 上 - 下 大
     * @return: 是否包含此数
     * @auther: kami
     * @date: 2020/4/11 22:43
     */
    public boolean Find(int target, int [][] array) {
        int rowLen = array.length,colLen = array[0].length;
        int row = 0,col = colLen-1;
        while (row<rowLen && col>=0){
            int num = array[row][col];
            if (num == target)return true;
            if (num > target){
                col--;
            }else {
                row++;
            }
        }
        return false;
    }

    /**
     * @description: 替换空格
     * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.
     * 则经过替换之后的字符串为We%20Are%20Happy。
     * @return:
     * @auther: kami
     * @date: 2020/4/11 22:57
     */
    public String replaceSpace(StringBuffer str) {
        StringBuilder stringBuilder = new StringBuilder();
        int lent = str.length();
        for (int i = 0; i < lent; i++) {
            char ichar = str.charAt(i);
            Object o = ichar == ' ' ? "%20":ichar;
            stringBuilder.append(o);
        }
        return stringBuilder.toString();
    }


   public static class ListNode {
       int val;
       ListNode next = null;
       ListNode(int val) {
           this.val = val;
       }
    }

    /**
     * @description: 从尾到头打印链表
     * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
     * @return:
     * @auther: kami
     * @date: 2020/4/11 23:05
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        while (listNode != null){
            list.add(listNode.val);
            listNode = listNode.next;
        }
        Collections.reverse(list);
        return list;
    }

     // Definition for binary tree
     public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    /**
     * @description: 重建二叉树
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
     * @return: 二叉树
     * @auther: kami
     * @date: 2020/4/11 23:13
     */
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        return reConstructBinaryTree(pre,0,pre.length-1,in,0,in.length-1);
    }

    private TreeNode reConstructBinaryTree(int [] pre,int startPre,int endPre,int [] in,int startIn,int endIn) {
        if(startPre>endPre||startIn>endIn)
            return null;
        TreeNode root=new TreeNode(pre[startPre]);
        for(int i=startIn;i<=endIn;i++)
            if(in[i]==pre[startPre]){
                root.left=reConstructBinaryTree(pre,startPre+1,startPre+i-startIn,in,startIn,i-1);
                root.right=reConstructBinaryTree(pre,i-startIn+startPre+1,endPre,in,i+1,endIn);
                break;
            }

        return root;
    }
}

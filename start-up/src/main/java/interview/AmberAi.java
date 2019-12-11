package interview;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AmberAi {
    //1.用两个栈实现队列

    private Stack<Integer> stack1 = new Stack<>();
    // 作为出队序列
    private Stack<Integer> stack2 = new Stack<>();
    public void push(int node) {
        // 入队时，要保证stack2为空
        while (!stack2.empty())
        {
            stack1.push(stack2.peek());
            stack2.pop();
        }
        stack1.push(node);
        System.out.println("入队元素是:" + stack1.peek());
    }

    public int pop() {
        // 出队时，要保证stack1为空
        while (!stack1.empty())
        {
            stack2.push(stack1.peek());
            stack1.pop();
        }
        System.out.println("出队元素是:" + stack2.peek());
        int temp = stack2.peek();
        stack2.pop();
        return temp;
    }

    //1.1扩展   两个队列实现栈
    /**
     * 剑指offer面试题7相关题目：用两个队列实现一个栈
     * 解题思路：根据栈的先入后出和队列的先入先出的特点
     * 在push的时候，把元素向非空的队列内添加
     * 在pop的时候，把不为空的队列中的size()-1份元素poll出来，添加到另为一个为空的队列中，再把队列中最后的元素poll出来
     * 两个队列在栈不为空的情况下始终是有一个为空，另一个不为空的。push添加元素到非空的队列中，pop把非空队列的元素转移到另一个空的队列中，
     * 直到剩下最后一个元素，这个元素就是要出栈的元素（最后添加到队列中的元素）。
     * @author GL
     *
     */
      private Queue<Object> queue1=new LinkedList<Object>();
      Queue<Object> queue2=new LinkedList<Object>();

    /*
     * 向队列中执行入栈操作时，把元素添加到非空的队列中
     */
    public  void push1(Object item){
        if(!queue1.isEmpty())
            queue1.offer(item);
        else
            queue2.offer(item);
        System.out.println("入栈元素为："+item);
    }

    public  void pop1(){
        if(!isEmpty()){
            if(queue1.isEmpty()){
                while(queue2.size()>1){
                    queue1.offer(queue2.poll());
                }
                System.out.println("出栈元素为："+queue2.poll());
            }else{
                while(queue1.size()>1){
                    queue2.offer(queue1.poll());
                }
                System.out.println("出栈元素为："+queue1.poll());
            }
        }
        else
            throw new RuntimeException("栈为空，无法执行出栈操作");
    }

    private  boolean isEmpty(){
        return queue1.isEmpty()&&queue2.isEmpty();
    }


    //2.给定一个字符串，实现它的全排列
    /*
     * 参数arrayA:给定字符串的字符数组
     * 参数start:开始遍历字符与其后面各个字符将要进行交换的位置
     * 参数end:字符串数组的最后一位
     * 函数功能：输出字符串数字的各个字符全排列
     */
    public static void recursionArrange(char[] arrayA,int start,int end){

        if(start == end){
            for(int i = 0;i < arrayA.length;i++)
                System.out.print(arrayA[i]);
            System.out.println();
        }
        else{
            for(int i = start;i <= end;i++){
                if (i != start) swap(arrayA,i,start); //交换
                recursionArrange(arrayA,start+1,end);//
                if (i != start) swap(arrayA,i,start);//换回来
            }
        }

    }
    //交换数组m位置和n位置上的值
    public static void swap(char[] arrayA,int m,int n){
        char temp = arrayA[m];
        arrayA[m] = arrayA[n];
        arrayA[n] = temp;
    }

}

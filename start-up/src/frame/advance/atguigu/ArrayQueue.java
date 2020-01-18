package frame.advance.atguigu;

public class ArrayQueue {
    public static void main(String[] args){


    }

    //模拟数组队列
    private int maxSize;//数组的最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//改数据用于存放数据，模拟队列

    //构造器
    public ArrayQueue(int ArrMaxSize){
        maxSize = ArrMaxSize;
        arr = new int[maxSize];
        front = -1;//
        rear = -1;
    }
    //判断队列是否满
    public boolean isFull(){
        return rear == maxSize-1;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加元素
    public void add(int n){
        //判断队列是否满
        if (isFull()){
            System.out.println("队列已满，无法添加");
            return;
        }
        rear++;
        arr[rear] = n;
    }

    //取元素，拿走数据
    public int get(){
        //判断队列是否空
        if (isEmpty()){
            //抛出异常
            throw new RuntimeException("队列空，不能获取");
        }
        front++;
        return arr[front];
    }

    //显示队列所有的数据
    public void showAll(){
        //遍历
        if (isEmpty()){
            System.out.println("队列空，没有数据");
            return;
        }
        for (int i = 0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }
    //显示队列的头数据，注意不是取出数据
    public int readHead(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，没有数据");
        }
        System.out.println(arr[front+1]);
        return arr[front+1];
    }
}

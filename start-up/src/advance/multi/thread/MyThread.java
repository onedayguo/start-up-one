package advance.multi.thread;

/**
 * @Description: 多线程测试
 * @Auther: kami
 * @Date: 2019/12/7 21:36
 * @Version: 1.0.0
 */
public class MyThread extends Thread {
    @Override
    public void run(){
        System.out.println("我是子线程的MyThread,lalalallalala");
        super.run();
    }
}

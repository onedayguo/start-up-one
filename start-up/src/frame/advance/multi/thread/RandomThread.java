package frame.advance.multi.thread;

/**
 * @Description: 随机睡眠一定时间，并打印当前线程的名字
 * @Auther: kami
 * @Date: 2019/12/7 21:50
 * @Version: 1.0.0
 */
public class RandomThread extends Thread {
    @Override
    public void run(){
        try {
            for (int i = 0; i < 10; i++) {
                int time = (int) (Math.random()*1000);
                Thread.sleep(time);
                System.out.println("子线程："+Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

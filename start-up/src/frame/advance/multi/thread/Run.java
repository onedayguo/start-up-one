package frame.advance.multi.thread;

/**
 * @Description: 测试多线程
 * @Auther: kami
 * @Date: 2019/12/7 21:38
 * @Version: 1.0.0
 */
public class Run {
    public static void main(String[] args) {
        try {
            RandomThread randomThread = new RandomThread();
            randomThread.setName("myThread");
            randomThread.start();
            for (int i = 0; i < 10; i++) {
                int time = (int) (Math.random()*1000);
                Thread.sleep(time);
                System.out.println("main线程："+Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

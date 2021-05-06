package lock;

/**
 * @Description: 线程交替打印ABC
 * @Author: kami
 * @Date: 2021/5/6 18:53
 * @Version: 1.0.0
 */
public class SynchronizedWaitNotifyThreadPrint {

    public static void main(String[] args) {
        Object o = new Object();
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i++ < 10){
                    synchronized (o){
                        System.out.println("A");
                        o.notify();
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i++ < 10){
                    synchronized (o){
                        System.out.println("B");
                        o.notify();
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        threadB.start();
        threadA.start();
    }
}

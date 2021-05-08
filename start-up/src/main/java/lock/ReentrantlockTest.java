package lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 可冲入锁实现线程交替执行
 * @Author: kami
 * @Date: 2021/5/8 16:31
 * @Version: 1.0.0
 */
public class ReentrantlockTest {


    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        // 控制执行顺序，唤醒
        Condition cA = lock.newCondition();
        Condition cB = lock.newCondition();
        Condition cC = lock.newCondition();
        // 控制首次执行
        CountDownLatch batchB = new CountDownLatch(1);
        CountDownLatch batchC = new CountDownLatch(1);
        Thread threadA = new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("A");
                    cB.signal();
                    if (i == 0) {
                        batchB.countDown();
                    }
                    cA.await();
                }
                // 最后停止
                cB.signal();
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        });
        Thread threadB = new Thread(() -> {
            try {
                batchB.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("B");
                    cC.signal();
                    if (i == 0) {
                        batchC.countDown();
                    }
                    cB.await();
                }
                // 最后停止
                cC.signal();
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        });
        Thread threadC = new Thread(() -> {
            try {
                batchC.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("C");
                    cA.signal();
                    cC.await();
                }
                // 最后停止
                cA.signal();
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        });

        threadC.start();
        Thread.sleep(1000);
        threadB.start();
        threadA.start();
    }
}

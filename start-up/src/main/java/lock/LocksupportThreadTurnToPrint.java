package lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @Description: 线程交替打印ABC
 * @Author: kami
 * @Date: 2021/5/6 18:35
 * @Version: 1.0.0
 */
public class LocksupportThreadTurnToPrint {
    static Thread threadA = null, threadB = null,threadC = null;
    private static volatile  int state = 0;
    public static void main(String[] args) {
        threadA = new Thread(() ->{
                while (state == 0){
                    System.out.println("A");
                    state = 1;
                    LockSupport.unpark(threadB);
                    LockSupport.park();
                }
        });

        threadB = new Thread(() ->{
            while (state == 1){

            }
        });
        threadC = new Thread(() ->{
                while (state == 2){
                    System.out.println("C");
                    state = 0;
                    LockSupport.unpark(threadA);
                    LockSupport.park();
                }

        });

        threadB.start();
        threadA.start();
        threadC.start();


    }
}

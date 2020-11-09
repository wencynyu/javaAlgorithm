package top.yuwenxin.jdk.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ABCReentrantLockPrinter {

    static Lock lock = new ReentrantLock(); // 重入锁用来实现互斥
    static Thread t1, t2, t3;

    static int syn = 0;  // syn用来实现同步

    public static void main(String[] args) {
        t1 = new Thread(() -> {
            while (true) {
                try {
                    lock.lock();
                    if (syn % 3 == 0) {
                        System.out.println("A");
                        Thread.sleep(500);
                        syn ++;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally{
                    lock.unlock();
                }
            }
        });

        t2 = new Thread(() -> {
            while (true) {
                try {
                    lock.lock();
                    if (syn % 3 == 1) {
                        System.out.println("B");
                        Thread.sleep(500);
                        syn ++;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally{
                    lock.unlock();
                }
            }

        });

        t3 = new Thread(() -> {
            while (true) {
                try {
                    lock.lock();
                    if (syn % 3 == 2) {
                        System.out.println("C");
                        Thread.sleep(500);
                        syn ++;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally{
                    lock.unlock();
                }
            }

        });


        t1.start();
        t2.start();
        t3.start();
    }
}

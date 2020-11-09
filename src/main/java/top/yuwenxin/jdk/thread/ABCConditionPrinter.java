package top.yuwenxin.jdk.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ABCConditionPrinter {
    static Lock lock = new ReentrantLock();
    static Condition conditionA = lock.newCondition();
    static Condition conditionB = lock.newCondition();
    static Condition conditionC = lock.newCondition();
    static Thread t1, t2, t3;

    static int syn = 0;
    public static void main(String[] args) {
        t1 = new Thread(() -> {

                try {
                    while (true) {
                        lock.lock();
                        while (syn % 3 != 0) {
                            conditionA.await();
                        }
                        System.out.println("A");
                        Thread.sleep(500);
                        syn ++;
                        conditionB.signal();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally{
                    lock.unlock();
                }

        });

        t2 = new Thread(() -> {

                try {
                    while (true) {
                        lock.lock();
                        while (syn % 3 != 1) {
                            conditionB.await();
                        }
                        System.out.println("B");
                        Thread.sleep(500);
                        syn ++;
                        conditionC.signal();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally{
                    lock.unlock();
                }

        });

        t3 = new Thread(() -> {

                try {
                    while (true) {
                        lock.lock();
                        while (syn % 3 != 2) {
                            conditionC.await();
                        }
                        System.out.println("C");
                        Thread.sleep(500);
                        syn ++;
                        conditionA.signal();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally{
                    lock.unlock();
                }

        });

        t1.start();
        t2.start();
        t3.start();
    }
}

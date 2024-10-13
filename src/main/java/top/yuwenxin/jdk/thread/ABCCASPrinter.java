package top.yuwenxin.jdk.thread;

import java.util.concurrent.atomic.AtomicReference;

public class ABCCASPrinter {

    enum Ready {
        T1, T2, T3
    }

    static volatile AtomicReference<Ready> ready = new AtomicReference<>(Ready.T1);

    public static void main(String[] args) {
        new Thread(() -> {

            try {
                while (true) {
                    while (ready.get() != Ready.T1) {
                    } // 多线程的死循环是可以改变的
                    System.out.println("A");
                    ready.set(Ready.T2);
                    Thread.sleep(500);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {

            try {
                while (true) {
                    while (ready.get() != Ready.T2) {
                    }
                    System.out.println("B");
                    ready.set(Ready.T3);
                    Thread.sleep(500);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {

            try {
                while (true) {
                    while (ready.get() != Ready.T3) {
                    }
                    System.out.println("C");
                    ready.set(Ready.T1);
                    Thread.sleep(500);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

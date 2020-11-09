package top.yuwenxin.jdk.thread;

public class ABCObjLockPrinter {
    static final Object lock = new Object();
    // wait和notify只能在同步代码块中使用
    static Thread t1, t2, t3;

    static int syn = 0;
    public static void main(String[] args) {
        t1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    while (true) {
                        if (syn % 3 == 0) {
                            System.out.println("A");
                            Thread.sleep(500);
                            syn ++;
                        }

                        lock.notifyAll();
                        lock.wait();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally{
                    lock.notifyAll();
                }
            }
        });

        t2 = new Thread(() -> {
            synchronized (lock) {
                try {
                    while (true) {
                        if (syn % 3 == 1) {
                            System.out.println("B");
                            Thread.sleep(500);
                            syn ++;
                        }

                        lock.notifyAll();
                        lock.wait();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally{
                    lock.notifyAll();
                }
            }
        });

        t3 = new Thread(() -> {
            synchronized (lock) {
                try {
                    while (true) {
                        if (syn % 3 == 2) {
                            System.out.println("C");
                            Thread.sleep(500);
                            syn ++;
                        }

                        lock.notifyAll();
                        lock.wait();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally{
                    lock.notifyAll();
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}

package top.yuwenxin.jdk.thread;


import java.util.concurrent.Semaphore;

public class ABCSemaphorePrinter {

    static Semaphore mute1 = new Semaphore(1), // 互斥信号量*1， 同步信号量*3
            s1 = new Semaphore(1), // 线程A，C之间的同步信号量，初始化为1，因为A线程要先运行
            s2 = new Semaphore(0),
            s3 = new Semaphore(0);

    static Thread t1, t2, t3;

    public static void main(String[] args) {

        t1 = new Thread(() -> {
            try {
                while(true){
                    s1.acquire(1);
                    mute1.acquire(1);
                    System.out.println("A");
                    Thread.sleep(1000);
                    mute1.release(1);
                    s2.release(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t2 = new Thread(() -> {
            try {
                while(true){
                    s2.acquire(1);
                    mute1.acquire(1);
                    System.out.println("B");
                    Thread.sleep(1000);
                    mute1.release(1);
                    s3.release(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t3 = new Thread(() -> {
            try {
                while(true){
                    s3.acquire(1);
                    mute1.acquire(1);
                    System.out.println("C");
                    Thread.sleep(1000);
                    mute1.release(1);
                    s1.release(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        t1.start();
        t2.start();
        t3.start();
    }

}

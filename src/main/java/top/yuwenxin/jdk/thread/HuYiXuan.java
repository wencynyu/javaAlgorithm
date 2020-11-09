package top.yuwenxin.jdk.thread;

import java.util.concurrent.Semaphore;

public class HuYiXuan {
    static Semaphore sync = new Semaphore(1);
    static Semaphore mute = new Semaphore(0);
    static int startInt = 1, endInt = 53;
    static char startChar = 'a', endChar = 'z';

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                while (startInt <= endInt){
                    sync.acquire(1);
                    mute.release(1);
                    if (startInt <= endInt) System.out.println(startInt++);
                    if (startInt <= endInt) System.out.println(startInt++);
                    sync.release(1);
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                while (startChar <= endChar){
                    mute.acquire(1);
                    sync.acquire(1);
                    System.out.println(startChar++);
                    sync.release(1);
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

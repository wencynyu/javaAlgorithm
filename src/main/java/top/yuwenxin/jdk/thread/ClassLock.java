package top.yuwenxin.jdk.thread;

import java.util.concurrent.TimeUnit;

public class ClassLock {
    private static final Object classLock = new Object();
    private final Object lock = new Object();

    public static void main(String[] args) {
        ClassLock instance = new ClassLock();
        new Thread(()->{
            ClassLock.classLock();
        }, "1").start();

        new Thread(()->{
            ClassLock.classLock();
        }, "2").start();

        new Thread(()->{
            instance.instanceLock();
        }).start();
    }

    static void classLock(){
        while (true){
            synchronized (classLock){
                System.out.println("类锁锁定" + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    void instanceLock(){
        while (true){
            synchronized (lock){
                System.out.println("对象锁锁定");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}

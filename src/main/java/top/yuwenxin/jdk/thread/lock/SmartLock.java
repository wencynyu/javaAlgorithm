package top.yuwenxin.jdk.thread.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class SmartLock {

    static class Sync extends AbstractQueuedSynchronizer {

        // 利用AQS中声明的state变量实现一个排他锁
        // state = 0：无线程抢占锁
        // state = 1：有线程抢占锁

        @Override
        protected boolean tryAcquire(int arg) {
            // 如果锁的拥有者是当前线程，直接返回true
            if (getExclusiveOwnerThread() == Thread.currentThread()) return true;
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            } else {
                return false;
            }
        }

        @Override
        protected boolean tryRelease(int arg) {
            // 释放锁
            // 将state置为0，拥有者置为null
            setState(0);
            setExclusiveOwnerThread(null);
            return true;
        }
    }

    private Sync mySync;

    public SmartLock() {
        mySync = new Sync();
    }

    public void lock() {
        mySync.acquire(1);  // 会调用mySync的tryAcquire()方法
    }

    public void unLock() {
        mySync.release(1);  // 会调用mySync的tryRelease()方法
    }
}

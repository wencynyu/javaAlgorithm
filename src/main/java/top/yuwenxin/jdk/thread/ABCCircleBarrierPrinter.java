package top.yuwenxin.jdk.thread;

import java.util.concurrent.CyclicBarrier;

public class ABCCircleBarrierPrinter {
    public static void main(String[] args) {
        // CyclicBarrier 底层仍然使用了ReentrantLock
        final CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
        final CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);
        final CyclicBarrier cyclicBarrier3 = new CyclicBarrier(3);

        Thread threadA = new Thread(() -> {
            try {
                while(true) {
                    System.out.println("A");
                    Thread.sleep(500);
                    // 控制A执行完在执行
                    cyclicBarrier1.await();
                    // 等待ABC都运行完
                    cyclicBarrier3.await();
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                while(true) {
                    cyclicBarrier1.await();
                    System.out.println("B");
                    Thread.sleep(500);
                    // 控制B执行完在执行C
                    cyclicBarrier2.await();
                    // 等待ABC都运行完
                    cyclicBarrier3.await();
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });

        Thread threadC = new Thread(() -> {
            try {
                while(true) {
                    cyclicBarrier2.await();
                    System.out.println("C");
                    Thread.sleep(500);
                    //等待ABC都运行完
                    cyclicBarrier3.await();
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }

        });

        threadC.start();
        threadB.start();
        threadA.start();
    }
}

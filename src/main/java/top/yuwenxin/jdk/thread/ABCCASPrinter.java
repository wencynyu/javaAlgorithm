package top.yuwenxin.jdk.thread;

public class ABCCASPrinter {

    enum Ready {
        T1, T2, T3
    }

    static volatile Ready ready = Ready.T1;

    public static void main(String[] args) {
        new Thread(() -> {

            try {
                while (true) {
                    while (ready != Ready.T1) {
                    } // 多线程的死循环是可以改变的
                    System.out.println("A");
                    ready = Ready.T2;
                    Thread.sleep(500);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {

            try {
                while (true) {
                    while (ready != Ready.T2) {
                    }
                    System.out.println("B");
                    ready = Ready.T3;
                    Thread.sleep(500);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {

            try {
                while (true) {
                    while (ready != Ready.T3) {
                    }
                    System.out.println("C");
                    ready = Ready.T1;
                    Thread.sleep(500);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

package top.yuwenxin.design.create;

/**
 * 1.懒汉
 * 2.饿汉
 *
 * 后面三种是默认线程安全的
 * 3.dcl
 * 4.内部类
 * 5.内部enum枚举实现：最优实现
 */
public class SingletonPattern {
    public static void main(String[] args) {
        int threadNum = 100;
        Thread[] threads = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            threads[i] = new Thread(()->{
                System.out.println(Singleton5.getInstance());
            });
            threads[i].start();
        }
    }
}


class Singleton1 {
    private static Singleton1 instance = new Singleton1();
    private Singleton1(){}

    public static Singleton1 getInstance() {
        return instance;
    }
}

class Singleton2 {
    private static Singleton2 instance = null;
    private Singleton2(){}

    public static Singleton2 getInstance() {
        if (instance == null){
            return new Singleton2();
        }
        return instance;
    }
}

class Singleton3 {
    private volatile static Singleton3 instance = null;
    private Singleton3(){}

    public static Singleton3 getInstance() {
        if (instance == null){
            synchronized (Singleton3.class){
                if (instance == null){
                    return new Singleton3();
                }
            }
        }
        return instance;
    }
}

class Singleton4 {
    static class Inner{
        private static Singleton4 instance = null;

        public static Singleton4 getInstance() {
            return new Singleton4();
        }
    }

    private Singleton4(){}

    public static Singleton4 getInstance() {
        return Inner.instance;
    }
}

class Singleton5{
    enum Inner{
        INSTANCE;
        private Singleton5 instance = new Singleton5();
    }
    private Singleton5(){}
    public static Singleton5 getInstance() {
        return Inner.INSTANCE.instance;
    }
}
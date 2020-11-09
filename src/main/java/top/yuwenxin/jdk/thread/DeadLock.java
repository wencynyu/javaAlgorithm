package top.yuwenxin.jdk.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class DeadLock {
    // 两支筷子
    // 两个人抢饭吃
    // 结果都饿死。。。
    static Semaphore s1 = new Semaphore(2);

    public static void main(String[] args) {


        new Thread(()->{
            while (true){
                try {
                    s1.acquire(1);
                    s1.acquire(1);
                    System.out.println("1吃饭");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("1吃完了");
                    s1.release(2);
                }
            }
        }).start();

        new Thread(()->{
            while (true){
                try {
                    s1.acquire(1);
                    s1.acquire(1);
                    System.out.println("2吃饭");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("2吃完了");
                    s1.release(2);
                }
            }
        }).start();
    }

    public static int[] solve(int[] arr){
        int[] res = new int[arr.length];
        List<Integer> _0 = new ArrayList<>();
        List<Integer> _not0 = new ArrayList<>();
        for (int i :
                arr) {
            if (i == 0) {
                _0.add(i);
            }else {
                _not0.add(i);
            }
        }
        for (int i = 0; i < _not0.size(); i++) {
            res[i] = _not0.get(i);
        }

        for (int i = 0; i < _0.size(); i++) {
            res[_not0.size() + i] = _0.get(i);
        }

        return res;
    }

}

package top.yuwenxin.jdk.thread.multi;

import top.yuwenxin.Main;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AsyncExecute {
    private static final Integer EXECUTE_GROUP_NUM = 4;

    private static final ThreadPoolExecutor EXECUTOR_ASYNC = new ThreadPoolExecutor(
            EXECUTE_GROUP_NUM, EXECUTE_GROUP_NUM,
            0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
            new NamedThreadFactory("async-worker", true)
    );
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        CompletableFuture
                .supplyAsync(() -> {
                    int i = 1;
                    try {
                        TimeUnit.SECONDS.sleep(i);
                        System.out.println("supply " + i);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return i;
                })
                .thenApplyAsync(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "apply " + i;
                }, EXECUTOR_ASYNC).thenAcceptAsync(s -> {
                    System.out.println("accept " + s);
                    System.out.println(Thread.currentThread().getName());
                    latch.countDown();
                });
        CompletableFuture
                .supplyAsync(() -> {
                    int i = 2;
                    try {
                        TimeUnit.SECONDS.sleep(i);
                        System.out.println("supply " + i);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return i;
                })
                .thenApplyAsync(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                        System.out.println(Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "apply " + i;
                }, EXECUTOR_ASYNC).thenAcceptAsync(s -> {
                    System.out.println("accept " + s);
                    System.out.println(Thread.currentThread().getName());
                    latch.countDown();
                });
        latch.await();
        System.out.println("test await");
    }

    public static class NamedThreadFactory implements ThreadFactory {

        private final AtomicInteger counter = new AtomicInteger();

        private final String namePrefix;

        private final boolean daemon;

        public NamedThreadFactory(String namePrefix, boolean daemon) {
            this.namePrefix = namePrefix;
            this.daemon = daemon;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName(namePrefix + "-" + counter.incrementAndGet());
            thread.setDaemon(daemon);
            return thread;
        }
    }
}

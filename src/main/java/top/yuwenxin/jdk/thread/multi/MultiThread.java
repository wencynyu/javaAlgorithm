package top.yuwenxin.jdk.thread.multi;

import com.google.common.collect.Lists;
import top.yuwenxin.Main;

import java.sql.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class MultiThread {
    private static final int _1M = 1024 * 1024;

    private static final Integer EXECUTE_GROUP_NUM = 4;
    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(
            EXECUTE_GROUP_NUM, EXECUTE_GROUP_NUM,
            0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
            new NamedThreadFactory("auto-update-worker", true)
    );

    private static final ScheduledExecutorService SCHEDULE = Executors.newScheduledThreadPool(4);

    private static final ScheduledExecutorService SINGLE_SCHEDULE = Executors.newSingleThreadScheduledExecutor();

    private static final AtomicInteger i = new AtomicInteger(1);

    private static final AtomicBoolean b = new AtomicBoolean(true);

    public static void main(String[] args) throws ExecutionException, InterruptedException, SQLException {

        // 两个定时任务
        // fixedDelay执行开始时间开始计算，结束后立马执行
        SCHEDULE.scheduleWithFixedDelay(() -> {
            System.out.println("test-" + i.getAndIncrement());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, 0, 1, TimeUnit.SECONDS);

        // fixedRate严格周期执行
        SCHEDULE.scheduleAtFixedRate(() -> {
            System.out.println("test-" + i.getAndIncrement());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, 0, 1, TimeUnit.SECONDS);


        // 线程池执行任务，通过AtomicBoolean类控制结束
        do {
            EXECUTOR.execute(() -> {
                while (b.get()) {
                    int andIncrement = i.getAndIncrement();
                    System.out.println(andIncrement + " " + Thread.currentThread());
                    if (andIncrement >= 100) {
                        b.compareAndSet(true, false);
                    }
                }
            });
        } while (b.get());

        //
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                List<Integer> list = Arrays.asList(11, 12, 13, 14, 15);
                int total = list.size();
                int groupSize = total / EXECUTE_GROUP_NUM + 1;
                executeAndWait(Lists.partition(list, groupSize), l -> {
                    try {
                        print(l);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }, EXECUTOR);
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private static void print(List<Integer> list) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println(list + " current thread is: " + Thread.currentThread());
    }

    public static <T> void executeAndWait(Collection<T> sourceData, Consumer<T> func, ThreadPoolExecutor executor) {
        if (!sourceData.isEmpty()) {
            System.out.println("start run...");
            CountDownLatch latch = new CountDownLatch(sourceData.size());
            AtomicReference<RuntimeException> throwableHolder = new AtomicReference<>();
            for (T data : sourceData) {
                executor.submit(() -> {
                    try {
                        func.accept(data);
                    } catch (RuntimeException t) {
                        throwableHolder.set(t);
                    } finally {
                        latch.countDown();
                    }
                });
            }

            try {
                System.out.println("latch await...");
                latch.await();
            } catch (InterruptedException e) {
                throwableHolder.set(new RuntimeException("Exception occurs while async executing.", e));
            }

            // stop and notify the chain if any exception occurs.
            if (throwableHolder.get() != null) {
                throw throwableHolder.get();
            }
        }
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

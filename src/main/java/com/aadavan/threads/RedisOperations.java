package com.aadavan.threads;

import java.util.concurrent.*;

public class RedisOperations {
    private static final int NO_OF_THREADS = 10;

    public static void main(String[] args) {
        ThreadPoolExecutor defaultPoolExecutor = constructThreadPool();
        Runnable task  = () -> {
            final String name = Thread.currentThread().getName();
            try {
                System.out.println("SLEEPING for 2 SECONDS. -> " + name);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Wakeup after 2 seconds. -> " + name);
        };
        for (int i = 0; i < NO_OF_THREADS; i++) {
            execute(defaultPoolExecutor, task);
        }
        defaultPoolExecutor.shutdown();
    }

    public static void execute(ThreadPoolExecutor executor, Runnable task) {
        executor.execute(task);
    }

    public static ThreadPoolExecutor constructThreadPool() {
        int coreSize = 2;
        int maxSize = 50;
        long timeOutTime = 2;
        TimeUnit unit = TimeUnit.SECONDS;
        ThreadPoolExecutor defaultPoolExecutor = new ThreadPoolExecutor(coreSize, maxSize, timeOutTime, unit, new LinkedBlockingQueue<Runnable>());
        NamedThreadFactory threadFactory = new NamedThreadFactory("default_pool_thread_");
        defaultPoolExecutor.setThreadFactory(threadFactory);
        return defaultPoolExecutor;
    }

}

package com.aadavan.threads.async;

import java.util.concurrent.*;

public class _1_RunnableTask {
    public static void main(String[] args) {
        singleThreadExecutor();
        cachedThreadPool();
        fixedThreadPool();
    }
    static void singleThreadExecutor() {
        Runnable task = () -> {
            System.out.println("Runnable task in singleThreadExecutor --> " + Thread.currentThread().getName());
        };
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(task);
        executorService.shutdown();
    }

    static void cachedThreadPool() {
        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("completed the execution in cachedThreadPool " + Thread.currentThread().getName());
        };
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(task);
        service.shutdown();
    }

    static void fixedThreadPool() {
        final ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(() -> {
            System.out.println("Completed the fixed thread pool task. " + Thread.currentThread().getName());
        });
        service.shutdown();
    }
}

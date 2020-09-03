package com.aadavan.threads.async;

import java.util.concurrent.*;

public class _1_RunnableTask {
    public static void main(String[] args) {
        Runnable task = () -> {
            System.out.println("Runnable task --> " + Thread.currentThread().getName());
        };
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(task);
        executorService.shutdown();
    }
}

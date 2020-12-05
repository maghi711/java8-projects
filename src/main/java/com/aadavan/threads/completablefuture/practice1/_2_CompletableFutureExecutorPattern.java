package com.aadavan.threads.completablefuture.practice1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class _2_CompletableFutureExecutorPattern {
    public static void main(String[] args) {
        Runnable task = () -> {
            System.out.println("I am running in a separater thread " + Thread.currentThread().getName());
        };
        ExecutorService service = Executors.newSingleThreadExecutor();
        CompletableFuture.runAsync(task, service);
        service.shutdown();
        //CompletableFuture.runAsync(task, service); // throws java.util.concurrent.RejectedExecutionException
        // Exception in thread "main" java.util.concurrent.RejectedExecutionException: Task java.util.concurrent.CompletableFuture$AsyncRun@7229724f rejected from java.util.concurrent.ThreadPoolExecutor@4c873330[Shutting down, pool size = 1, active threads = 1, queued tasks = 0, completed tasks = 0]
    }
}

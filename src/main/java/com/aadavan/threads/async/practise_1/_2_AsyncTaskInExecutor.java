package com.aadavan.threads.async.practise_1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class _2_AsyncTaskInExecutor {
    public static void main(String[] args) {
        Runnable task = () -> System.out.println("Hello World " + Thread.currentThread().getName());
        ExecutorService service = Executors.newSingleThreadExecutor();
        CompletableFuture.runAsync(task, service); // Runs in the above Executor
        CompletableFuture.runAsync(task); // Runs in Common Fork Join Pool
        service.shutdown();
    }
}

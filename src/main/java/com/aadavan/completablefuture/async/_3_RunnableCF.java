package com.aadavan.completablefuture.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class _3_RunnableCF {
    public static void main(String[] args) {
        _3_RunnableCF instance = new _3_RunnableCF();
        Runnable task = () -> System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        //instance.plainCall(task);
        instance.withExecutorService(task);
    }

    void withExecutorService(Runnable task) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        CompletableFuture.runAsync(task, service);
        service.shutdown();
    }

    void plainCall(Runnable task) {
        CompletableFuture.runAsync(task);
        // It does print until delay is done.
        //ThreadUtils.delay(1);
    }
}

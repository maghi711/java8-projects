package com.aadavan.completablefuture.async;

import com.aadavan.completablefuture.utils.ThreadUtils;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class _2_SecondRunnableCF {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        // Runs in common thread pool
        Runnable task = () -> {
            ThreadUtils.delay(1, TimeUnit.SECONDS);
            System.out.println("Time is --> " + new Date() + " \t in the thread pool " + Thread.currentThread().getName());
        };
        CompletableFuture.runAsync(task, service);
        service.shutdown();
    }
}

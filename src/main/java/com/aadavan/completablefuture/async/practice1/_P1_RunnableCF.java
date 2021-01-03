package com.aadavan.completablefuture.async.practice1;

import com.aadavan.completablefuture.utils.ThreadUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class _P1_RunnableCF {

    public static void main(String[] args) {
        firstWay();
        secondWay();
    }

    private static void secondWay() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        CompletableFuture.runAsync(() -> System.out.println("Runnable task completed.\t" + Thread.currentThread().getName()), service);
        service.shutdown();
    }

    private static void firstWay() {
        CompletableFuture.runAsync(() -> System.out.println("Runnable task completed.\t" + Thread.currentThread().getName()));
        ThreadUtils.delay(10, TimeUnit.MILLISECONDS);
    }

    public static void main_(String[] args) {
        Runnable task = () -> System.out.println("Executed a task, bye");
        CompletableFuture.runAsync(task);
        ThreadUtils.delay(1, TimeUnit.SECONDS);
        //System.out.println("Main task executed");
    }
}

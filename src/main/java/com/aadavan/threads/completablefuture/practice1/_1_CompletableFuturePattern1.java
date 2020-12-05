package com.aadavan.threads.completablefuture.practice1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class _1_CompletableFuturePattern1 {
    public static void main(String[] args) {
        Runnable task = () -> {
            System.out.println("I am running in a separater thread " + Thread.currentThread().getName());
        };
        // Runs in a separate daemon thread, so if main thread exits then this also exits.
        CompletableFuture.runAsync(task);
        // To get the system out message used in the task, just uncomment the below line.
        sleep(100);
    }

    private static void sleep(long timeout) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

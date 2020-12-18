package com.aadavan.threads.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class _2_CompletableFutureRunnablePattern {
    public static void main(String[] args) {
        System.out.println("Starting the tasks " + Thread.currentThread().getName());
        CompletableFuture.runAsync(() -> {
            System.out.println("Aadavan how are you doing? " + Thread.currentThread().getName());
        });
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

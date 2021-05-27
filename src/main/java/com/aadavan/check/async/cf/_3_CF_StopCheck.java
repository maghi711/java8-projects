package com.aadavan.check.async.cf;

import com.aadavan.completablefuture.utils.ThreadUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class _3_CF_StopCheck {
    public static void main(String[] args) {
        System.out.println("Execution started");
        final long start = System.currentTimeMillis();
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> {
                     ThreadUtils.delay(10, TimeUnit.SECONDS);
                     return "Hello";
                });
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> {
                    ThreadUtils.delay(10, TimeUnit.SECONDS);
                    return "Beautiful";
                });
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture
                = CompletableFuture.allOf(future1, future2, future3);
        try {
            combinedFuture.get(2, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            final long end = System.currentTimeMillis();
            System.out.println(end - start);
        }

        System.out.println(future1.isDone());
        System.out.println(future2.isDone());
        System.out.println(future3.isDone());
    }
}

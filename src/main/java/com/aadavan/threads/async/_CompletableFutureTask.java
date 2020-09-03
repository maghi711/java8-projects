package com.aadavan.threads.async;

import java.util.concurrent.*;

public class _CompletableFutureTask {
    public static void main(String[] args) {
        Runnable task = () -> {
            System.out.println("Runnable task --> " + Thread.currentThread().getName());
        };
        CompletableFuture<?> completableFuture = CompletableFuture.runAsync(task); // Takes a Runnable
        // Takes a Supplier<T> NOT a Callable<T>
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Supply Async --> " + Thread.currentThread().getName());
            return null;
        }, Executors.newSingleThreadExecutor());
        try {
            final Object o = completableFuture.get(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}

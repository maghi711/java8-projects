package com.aadavan.threads.async.practise_1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class _3_CompletableFutureTask {
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) {
        System.out.println("Main Started");
        //helloCompletableFutureSupplyAsync();
        //helloCFSupplyAsyncSleep();
        //withExecutor();
        withExecutorAndForceCompletion();
    }

    private static void helloCFSupplyAsyncSleep() {
        Supplier<String> supplier = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getName();
        };
        CompletableFuture<String> future = CompletableFuture.supplyAsync(supplier);
        String string = future.join(); // Blocking call
        System.out.println("Result = " + string);
    }

    static void helloCompletableFutureSupplyAsync() {
        Supplier<String> supplier = () -> Thread.currentThread().getName();
        CompletableFuture<String> future = CompletableFuture.supplyAsync(supplier);
        String string = future.join(); // Blocking call
        System.out.println("Result = " + string);
    }

    private static void withExecutor() {
        Supplier<String> supplier = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getName();
        };
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        CompletableFuture<String> future = CompletableFuture.supplyAsync(supplier, executor);
        String string = future.join(); // Blocking call
        System.out.println("Result = " + string);
        executor.shutdown();
    }

    private static void withExecutorAndForceCompletion() {
        Supplier<String> supplier = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getName();
        };
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        CompletableFuture<String> future = CompletableFuture.supplyAsync(supplier, executor);
        future.complete("Too long, force completion");
        String string = future.join(); // Blocking call
        System.out.println("Result = " + string);
        executor.shutdown();
    }

}

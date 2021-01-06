package com.aadavan.completablefuture.async.practice1;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class _P1_SupplierCF {

    public static void main(String[] args) {
        executeInDefaultCommonForkJoinPool();
        executeInAnExecutorPool();
    }

    private static void executeInDefaultCommonForkJoinPool() {
        Supplier<String> supplier = () -> Thread.currentThread().getName();
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(supplier);
        String join = stringCompletableFuture.join();
        System.out.println(join);
    }

    private static void executeInAnExecutorPool() {
        Supplier<String> supplier = () -> Thread.currentThread().getName();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CompletableFuture<String> scf = CompletableFuture.supplyAsync(supplier, executor);
        String join = scf.join();
        System.out.println(join);
        executor.shutdown();
    }

    public static void main_(String[] args) throws InterruptedException, ExecutionException {
        Supplier<String> supplierTask = () -> "Hello Aadavan, the date and time now is " + new Date();
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(supplierTask);
        String s = cf.get();
        System.out.println(s);
    }

}

package com.aadavan.completablefuture.async.practice1;

import com.aadavan.completablefuture.utils.ThreadUtils;

import java.util.Date;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class _P1_CompleteAndObtrudeSupplierCF {

    public static void main(String[] args) {
        firstWay();
        secondWay();
    }

    private static void firstWay() {
        Supplier<String> supplier = () -> {
            ThreadUtils.delay(500, TimeUnit.MILLISECONDS);
            return Thread.currentThread().getName();
        };
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(supplier);
        // Waits till the execution is completed in the thread if used before the complete or obtrude call
        String join = stringCompletableFuture.join();
        System.out.println(join);
        stringCompletableFuture.complete("Too Long!");
        join = stringCompletableFuture.join();
        System.out.println(join);
    }

    private static void secondWay() {
        Supplier<String> supplier = () -> Thread.currentThread().getName();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CompletableFuture<String> scf = CompletableFuture.supplyAsync(supplier, executor);
        String join = scf.join();
        System.out.println(join);
        scf.obtrudeValue("Too Long!");
        join = scf.join();
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

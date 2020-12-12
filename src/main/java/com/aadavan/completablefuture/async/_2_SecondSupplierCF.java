package com.aadavan.completablefuture.async;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class _2_SecondSupplierCF {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();

        Supplier<String> supplier = () -> {
            return new Date().toString() + " " + Thread.currentThread().getName();
        };
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(supplier, service);
        String join = stringCompletableFuture.join();
        System.out.println("join = " + join);
        service.shutdown();
    }
}

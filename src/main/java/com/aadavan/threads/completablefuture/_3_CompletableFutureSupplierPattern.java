package com.aadavan.threads.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class _3_CompletableFutureSupplierPattern {
    public static void main(String[] args) {
        System.out.println("Starting the tasks " + Thread.currentThread().getName());
        Supplier<String> task = () -> {
            System.out.println("Aadavan how are you doing? " + Thread.currentThread().getName());
            return "";
        };
        CompletableFuture.supplyAsync(task);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

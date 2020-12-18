package com.aadavan.threads.completablefuture.practice1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class _3_CompletableFutureWIthSupplier {
    public static void main(String[] args) {
        Supplier<String> supplier = () -> "Thread name is " + Thread.currentThread().getName();
        CompletableFuture<String> result = CompletableFuture.supplyAsync(supplier);
        try {
            System.out.println("result = " + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

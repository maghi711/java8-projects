package com.aadavan.completablefuture.async;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class _1_FirstSupplierCF {
    public static void main(String[] args) {
        Supplier<String> suplier = () -> {
            Date x = new Date();
            System.out.println(x + " " + Thread.currentThread().getName());
            return x.toString();
        };
        CompletableFuture<String> asyncResult = CompletableFuture.<String>supplyAsync(suplier);
        String asyncString = asyncResult.join();
        System.out.println("asyncString = " + asyncString);
    }
}

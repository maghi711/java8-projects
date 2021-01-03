package com.aadavan.completablefuture.async.practice1;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class _P1_SupplierCF {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Supplier<String> supplierTask = () -> "Hello Aadavan, the date and time now is " + new Date();
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(supplierTask);
        String s = cf.get();
        System.out.println(s);
    }
}

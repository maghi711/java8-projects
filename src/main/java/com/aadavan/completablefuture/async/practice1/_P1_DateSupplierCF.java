package com.aadavan.completablefuture.async.practice1;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class _P1_DateSupplierCF {
    public static void main(String[] args) throws Exception {
        Supplier<Date> dateSupplier = () -> new Date();
        CompletableFuture<Date> dateCompletableFuture = CompletableFuture.supplyAsync(dateSupplier);
        Date date = dateCompletableFuture.get();
        System.out.println("Aadavan the current date and time is " + date);
    }
}

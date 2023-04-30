package com.aadavan.completablefuture.async.practice1;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class _P1_DateSupplierCF2 {
    public static void main(String[] args) throws Exception {
        // Supplier can supply any object, Date is supplied in the below example
        System.out.println("checking");
        provideADateUsingSupplier();
        TimeUnit.SECONDS.sleep(20);
        System.out.println("done");
    }

    private static void provideADateUsingSupplier() throws InterruptedException, java.util.concurrent.ExecutionException {
        Supplier<Date> dateSupplier = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (true)
                throw new RuntimeException("unkonwn exception");
            return new Date();
        };
        try {
            CompletableFuture<Date> dateCompletableFuture = CompletableFuture.supplyAsync(dateSupplier);
            Date date = dateCompletableFuture.get();
            System.out.println("Aadavan the current date and time is " + date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

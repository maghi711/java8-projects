package com.aadavan.threads.async;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class _4_LaunchAsyncTask {
    public static void main(String[] args) {
        System.out.println("Async logic");
        Supplier<List<Long>> longSupplier = () -> {
            System.out.println("Supplier --> Producing the primary id's from various sources.");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Arrays.asList(10L, 5L, 25L, 30L);
        };
        CompletableFuture<List<Long>> cf = CompletableFuture.supplyAsync(longSupplier).thenApply(list -> printUsers(list));
        List<Long> longs=null;
        try {
            longs = cf.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static List<Long> printUsers(List<Long> list) {
        System.out.println("Print Users");
        System.out.println("longs = " + list);
        return null;
    }
}

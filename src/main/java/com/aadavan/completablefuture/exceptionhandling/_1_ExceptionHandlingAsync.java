package com.aadavan.completablefuture.exceptionhandling;

import com.aadavan.completablefuture.utils.ThreadUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class _1_ExceptionHandlingAsync {
    public static void main(String[] args) {
        Supplier<List<Long>> suppliedIds = () -> {
            return Arrays.asList(1L, null, 2L);
        };
        CompletableFuture<List<Long>> listCompletableFuture = CompletableFuture.supplyAsync(suppliedIds);
        CompletableFuture<List<Long>> exceptionally = listCompletableFuture.exceptionally(e -> Arrays.asList(1L, 2L, 3L));
        CompletableFuture<Void> result = exceptionally.thenAccept(ids -> {
            for (Long l : ids) {
                try {
                    System.out.println("running for " + l);
                    System.out.println(100 * l);
                } catch (Exception e) {
                    System.out.println("Exception occurred: " + e.getMessage());
                }
            }
        });
        System.out.println(result.isCompletedExceptionally());
        ThreadUtils.delay(200, TimeUnit.MILLISECONDS);
    }
}

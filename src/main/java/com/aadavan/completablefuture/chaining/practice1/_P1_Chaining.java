package com.aadavan.completablefuture.chaining.practice1;

import com.aadavan.completablefuture.utils.ThreadUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class _P1_Chaining {
    public static void main(String[] args) {
        Supplier<List<Long>> ages = () -> {
            System.out.println(getCurrentThreadName()+ " Supplier --> Fetching all the ages from the DB.\n");
            return Arrays.asList(7l, 35l, 36l);
        };
        CompletableFuture.supplyAsync(ages).thenRunAsync(
                () -> System.out.println(getCurrentThreadName() + " Runnable --> Completed fetching ages from DB.\n"));
        ThreadUtils.delay(1, TimeUnit.SECONDS);
    }

    public static String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }
}

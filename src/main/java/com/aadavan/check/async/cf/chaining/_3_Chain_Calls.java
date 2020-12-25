package com.aadavan.check.async.cf.chaining;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class _3_Chain_Calls {
    public static void main(String[] args) {
        CompletableFuture
            .supplyAsync(() -> Arrays.asList(100L, 200L, 300L))
            .thenApply(id -> id.stream().map(_3_Chain_Calls::newLong).collect(Collectors.toList()))
            .thenAccept(id -> id.forEach(System.out::println));
    }

    private static Long newLong(Long id) {
        return id * 50/100;
    }
}

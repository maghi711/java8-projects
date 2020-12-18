package com.aadavan.check.async.cf.chaining;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class _3_Chain_Calls {
    public static void main(String[] args) {
        CompletableFuture
            .supplyAsync(() -> Arrays.asList(1L, 2L, 3L))
            .thenApply(id -> id.stream().map(Long::new))
            .thenAccept(id -> id.forEach(System.out::println));
    }
}

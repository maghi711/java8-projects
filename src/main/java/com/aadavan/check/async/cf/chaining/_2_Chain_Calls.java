package com.aadavan.check.async.cf.chaining;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class _2_Chain_Calls {
    public static void main(String[] args) {
        CompletableFuture
                .supplyAsync(() -> Arrays.asList(1L, 2L, 3L))
                .thenApply(ids -> ids.stream().map(User::new).collect(Collectors.toList()))
                .thenAccept(id -> id.forEach(System.out::print));
    }
}

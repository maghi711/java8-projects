package com.aadavan.completablefuture.chaining.practice1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class _2_ChainTasks {
    public static void main(String[] args) {
        Consumer<List<Long>> data = values -> {
            values.forEach(System.out::println);
        };
        Supplier<List<Long>> ids = () -> {
            System.out.println("Fetching all the ids.");
            return Arrays.asList(1L, 2L, 3L);
        };
        final CompletableFuture<List<Long>> listCompletableFuture = CompletableFuture.supplyAsync(ids);
        listCompletableFuture.thenAccept(data);
        Function<List<Long>, List<Long>> convert = (uIds) -> {
            System.out.println("converting the data");
            return uIds.stream().map(
                    val -> val * 10
            ).collect(Collectors.toList());
        };
        listCompletableFuture.thenApply(convert).thenAccept(data);
        listCompletableFuture.join();
    }
}

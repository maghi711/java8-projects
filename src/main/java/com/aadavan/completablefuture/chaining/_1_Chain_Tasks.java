package com.aadavan.completablefuture.chaining;

import com.aadavan.completablefuture.utils.ThreadUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class _1_Chain_Tasks {
    public static void main(String[] args) {
        // first task - fetch the list of ids
        Supplier<List<Long>> supplyIDs = () -> {
            ThreadUtils.delay(200, TimeUnit.MILLISECONDS);
            return Arrays.asList(1L, 2L, 3L);
        };

        // second task - construct/fetch the users based on the available ids
        Function<List<Long>, List<User>> fetchUsers = ids -> {
            ThreadUtils.delay(300, TimeUnit.MILLISECONDS);
            return ids.stream().map(User::new).collect(Collectors.toList());
        };

        // print all of the available users
        Consumer<List<User>> display = users -> users.forEach(System.out::println);

        // Execute this in a completable future
        CompletableFuture<List<Long>> cf = CompletableFuture.supplyAsync(supplyIDs);
        cf.thenApply(fetchUsers).thenAccept(display);

        ThreadUtils.delay(1000, TimeUnit.MILLISECONDS);
    }
}

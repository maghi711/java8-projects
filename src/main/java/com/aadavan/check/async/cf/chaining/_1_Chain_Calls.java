package com.aadavan.check.async.cf.chaining;

import com.aadavan.completablefuture.utils.ThreadUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class _1_Chain_Calls {
    public static void main(String[] args) {
        // get ids
        Supplier<List<Long>> ids = () -> {
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            return Arrays.asList(1L, 2L, 3L);
        };

        // get users based on ids
        Function<List<Long>, List<User>> users = userIds ->  {
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            return userIds.stream().map(User::new).collect(Collectors.toList());
        };

        // print them
        Consumer<List<User>> printUsers = s -> {
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            s.forEach(System.out::println);
        };

        CompletableFuture.supplyAsync(ids).thenApply(users).thenAccept(printUsers);

        ThreadUtils.delay(1, TimeUnit.SECONDS);
    }
}

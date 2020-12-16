package com.aadavan.check.async.cf;

import com.aadavan.completablefuture.utils.ThreadUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class _2_CFServiceable {
    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Supplier<String> task = () -> {
            System.out.println("Returning the name ");
            return "Aadavan";
        };
        final CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(task, service);
        //System.out.println(stringCompletableFuture.get());
        //ThreadUtils.delay(1);
        service.shutdown();
    }
}

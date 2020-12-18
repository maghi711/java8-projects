package com.aadavan.completablefuture.async;

import com.aadavan.completablefuture.utils.ThreadUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class _3_ServicableCF {
    public static void main(String[] args) {
        _3_ServicableCF instance = new _3_ServicableCF();
        Supplier<String> stringSupplier = () -> {
            ThreadUtils.delay(1, TimeUnit.SECONDS);
            return Thread.currentThread().getName();
        };
        //instance.plain(stringSupplier);;
        instance.withExecutors(stringSupplier);
    }

    void plain(Supplier<String> task) {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(task);
        String join = completableFuture.join();
        System.out.println("join = " + join);
    }

    void withExecutors(Supplier<String> task) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(task, service);
        System.out.println(completableFuture.join());
        completableFuture.obtrudeValue("complete execution");
        System.out.println(completableFuture.join());
        service.shutdown();
    }
}

package com.aadavan.completablefuture.async.practice1;

import com.aadavan.completablefuture.utils.ThreadUtils;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class _P1_ReturnValueInRunnableCF {
    public static void main(String[] args) {
        executeARunnableAndGetBackTheResult();
    }

    private static void executeARunnableAndGetBackTheResult() {
        CompletableFuture<String> cf = new CompletableFuture<>();
        Runnable task = () -> {
            ThreadUtils.delay(100, TimeUnit.MILLISECONDS);
            cf.complete("Aadavan the time is " + new Date());
        };
        CompletableFuture.runAsync(task);
        String result = cf.join();
        System.out.println(result);
    }
}

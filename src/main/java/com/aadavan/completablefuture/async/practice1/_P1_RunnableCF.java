package com.aadavan.completablefuture.async.practice1;

import com.aadavan.completablefuture.utils.ThreadUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class _P1_RunnableCF {
    public static void main(String[] args) {
        Runnable task = () -> System.out.println("Executed a task, bye");
        CompletableFuture.runAsync(task);
        ThreadUtils.delay(1, TimeUnit.SECONDS);
        //System.out.println("Main task executed");
    }
}

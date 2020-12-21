package com.aadavan.completablefuture.async;

import com.aadavan.completablefuture.utils.ThreadUtils;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class _1_FIrstRunnableCF {
    public static void main(String[] args) {
        // Runs in Fork Join Pool
        CompletableFuture.runAsync(() -> {
            ThreadUtils.delay(1, TimeUnit.SECONDS);
            System.out.println("Time is --> " + new Date() + " \t in the thread pool " + Thread.currentThread().getName());
        });
        ThreadUtils.delay(2, TimeUnit.SECONDS);
    }
}

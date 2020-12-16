package com.aadavan.check.async.cf;

import com.aadavan.completablefuture.utils.ThreadUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class _1_CFRunnable {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Runnable task = () -> {
            ThreadUtils.delay(1);
            for (int i = 0; i < 5; i++) {
                System.out.println("The count is " + i);
            }
        };
        CompletableFuture.runAsync(task, service);
        //ThreadUtils.delay(2);
        service.shutdown();
    }
}

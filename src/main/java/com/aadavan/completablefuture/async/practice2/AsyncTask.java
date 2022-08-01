package com.aadavan.completablefuture.async.practice2;

import com.aadavan.completablefuture.utils.ThreadUtils;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class AsyncTask {

    public static void main(String[] args) {
        final Future<String> stringFuture = Timer.asyncCurrentTime();
        final String time;
        try {
            time = stringFuture.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        System.out.println("time = " + time);
    }
}

class Timer {
    public static String currentTime() {
        ThreadUtils.delay(2, TimeUnit.SECONDS);
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        return LocalDateTime.now().toString();
    }

    public static Future<String> asyncCurrentTime() {
        return CompletableFuture.supplyAsync(() -> currentTime());
    }
}

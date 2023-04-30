package com.aadavan.completablefuture.practice4;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Calculator {

    public static void main(String[] args) {
        System.out.println("Execution started");
        Calculator calculator = new Calculator();
        //syncAdd(calculator);
        asyncAdd(calculator);
        System.out.println("Execution ended");
    }

    private static void asyncAdd(Calculator calculator) {
        Future<Long> result = calculator.addAsync(1, 2);
        try {
            final Long aLong = result.get();
            System.out.println("aLong = " + aLong);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static void syncAdd(Calculator calculator) {
        long result = calculator.add(1, 2);
        System.out.println("result = " + result);
    }

    private Future<Long> addAsync(int a, int b) {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return CompletableFuture.supplyAsync(() -> add(a, b));
    }

    private long add(int a, int b) {
        return a + b;
    }
}

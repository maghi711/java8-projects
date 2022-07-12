package com.aadavan.completablefuture.practice2;

import com.aadavan.completablefuture.utils.ThreadUtils;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class AsyncTaskPractice2 {
    static {
        BasicConfigurator.configure();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncTaskPractice2.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        LOGGER.info("Execution started...");

        P2Shop shop = new P2Shop();
        long start = System.nanoTime();
        Future<Double> doubleFuture = shop.getAsyncPrice("ABC");
        LOGGER.info("Invocation Time taken is {} milli seconds", (System.nanoTime()-start) / 1_000_000);

        // doSomethingElse()
        doSomething();

        // while the price is being calculated
        try {
            double price = doubleFuture.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long end = System.nanoTime();
        LOGGER.info("Time taken is {} milli seconds", (end-start) / 1_000_000);
        LOGGER.info("Execution ended...");
    }

    private static void doSomething() {
        LOGGER.info("Doing something");
        ThreadUtils.delay(1, TimeUnit.SECONDS);
    }

    static void basicTest() throws ExecutionException, InterruptedException {
        P2Shop shop = new P2Shop();
        LOGGER.info("Get next price {}", shop.getPrice("ABC"));
        final Future<Double> asyncPrice = shop.getAsyncPrice("");
        while (true) {
            boolean check = asyncPrice.isDone();
            if (check) {
                LOGGER.info("Calculated pride is {}", asyncPrice.get());
                break;
            }
            else {
                LOGGER.info("Still not yet done");
                ThreadUtils.delay(1, TimeUnit.SECONDS);
            }
        }
    }
}

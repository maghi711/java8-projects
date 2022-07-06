package com.aadavan.completablefuture.async;

import com.aadavan.completablefuture.utils.ThreadUtils;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class A1FirstRunnableCF {

    private static final Logger LOGGER = LoggerFactory.getLogger(A1FirstRunnableCF.class);
    public static void main(String[] args) {
        BasicConfigurator.configure();
        practice();
    }

    public static void practice() {
        executeRunnable();
        executeSupplier();
    }

    private static void executeSupplier() {
        Supplier<String> supplier = () -> {
            LOGGER.info(getCurrentTime());
            return "Aadavan";
        };
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(supplier);
        String result = stringCompletableFuture.join();
        LOGGER.info("My name is {}", result);
    }

    private static void executeRunnable() {
        Runnable task = () -> LOGGER.info(getCurrentTime());
        // Runs in Fork Join Pool
        CompletableFuture.runAsync(task);
        ThreadUtils.delay(2, TimeUnit.SECONDS);
    }

    private static String getCurrentTime() {
        return "The current time is --> " + new Date();
    }
}

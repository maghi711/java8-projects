package com.aadavan.completablefuture.practice3;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class ExposeSyncToAsync {
    static {
        BasicConfigurator.configure();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ExposeSyncToAsync.class);

    public static void main(String[] args) {
        multiTasks();
    }

    static void multiTasks() {
        List<P3Shop> shops = Arrays.asList(
                new P3Shop(""),
                new P3Shop(""),
                new P3Shop(""),
                new P3Shop(""),
                new P3Shop("")
        );

        final long start = System.nanoTime();
        shops.parallelStream().forEach(p3Shop -> {
            Future<Double> doubleFuture = p3Shop.getPriceAsync("ABC");
            LOGGER.info("Invocation Time taken is {} milli seconds", (System.nanoTime() - start) / 1_000_000);
            // while the price is being calculated
            try {
                double price = doubleFuture.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        LOGGER.info("Total Time taken is {} milli seconds", (System.nanoTime() - start) / 1_000_000);
    }

    static void singleTask() {
        P3Shop shop = new P3Shop("MiA1");
        long start = System.nanoTime();
        final double miA1 = shop.getPrice("MiA1");
        long end = System.nanoTime();
        LOGGER.info("miA1 = {}", miA1);
        LOGGER.info("Time Taken {}", (end - start) / 1_000_000);
    }
}

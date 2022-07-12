package com.aadavan.completablefuture.practice2;

import com.aadavan.completablefuture.utils.ThreadUtils;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class P2Shop {
    private static final Logger LOGGER = LoggerFactory.getLogger(P2Shop.class);

    private static Random random = new Random();


    public double getPrice(String product) {
        ThreadUtils.delay(1, TimeUnit.SECONDS);
        return random.nextDouble();
    }

    public Future<Double> getAsyncPrice(String product) {
        LOGGER.info("Calculation started");
        return CompletableFuture.supplyAsync(() -> getPrice(product));
    }
}

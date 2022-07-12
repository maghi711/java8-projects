package com.aadavan.completablefuture.practice3;


import com.aadavan.completablefuture.utils.ThreadUtils;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class P3Shop {

    private static Random random = new Random();
    private String name;

    public P3Shop(String name) {
        this.name = name;
    }

    public double getPrice(String product) {
        ThreadUtils.delay(1, TimeUnit.SECONDS);
        return random.nextDouble();
    }

    public Future<Double> getPriceAsync(String product) {
        return CompletableFuture.supplyAsync(() -> getPrice(product));
    }
}

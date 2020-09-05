package com.aadavan.completablefuture.two;

import com.aadavan.completablefuture.utils.ThreadUtils;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class TwoShop {
    private String name;
    private Random random = new Random();

    public TwoShop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> calculatePrice(product));
        /*
        CompletableFuture<Double> future = new CompletableFuture<>();
        new Thread(
            () -> {
                try {
                    final double v = calculatePrice(product);
                    future.complete(v);
                } catch (Exception e) {
                    future.completeExceptionally(e);
                }
            }
        ).start();
        */
        return future;
    }

    /**
     * A synchronous call to get the price of a product.
     * @return
     */
    public double getPrice(String product) {
        return calculatePrice(product);
    }

    private double calculatePrice(String product) {
        ThreadUtils.delay(10);
        if (false)
            throw new RuntimeException("Testing");
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

}

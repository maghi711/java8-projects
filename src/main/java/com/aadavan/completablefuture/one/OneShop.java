package com.aadavan.completablefuture.one;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class OneShop {

    private String name;

    public OneShop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Asynchronously calling the sync api with CompletableFuture<T>
     * @param product
     * @return
     */
    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> future = new CompletableFuture<>();
        new Thread(
                () -> {
                    double price = calculatePrice(product);
                    future.complete(price);
                }
        ).start();
        return future;
    }

    /**
    synchronously calling the api
     */
    public double getPrice(String product) {
        return calculatePrice(product);
    }

    private double calculatePrice(String product) {
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ie) {
            throw new RuntimeException();
        }
    }
}

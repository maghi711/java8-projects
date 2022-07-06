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
        CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> calculatePrice(product));
        // The above single line replaces the below set of steps.
/*      CompletableFuture<Double> future = new CompletableFuture<>();
        new Thread(
                () -> {
                    try {
                        double price = calculatePrice(product);
                        future.complete(price);
                    } catch (Exception e) {
                        future.completeExceptionally(e);
                    }
                }
        ).start();*/
        return future;
    }

    /**
    synchronously calling the api
     */
    public double getPrice(String product) {
        return calculatePrice(product);
    }

    private double calculatePrice(String product) {
        delay(1);
        /*
        if ("Flipkart".equalsIgnoreCase(product)) {
            throw new RuntimeException("Unavailable");
        }
        */
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay(long delayInSeonds) {
        try {
            TimeUnit.SECONDS.sleep(delayInSeonds);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}

package com.aadavan.lambdas.java8InAction.practise1;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static com.aadavan.lambdas.java8InAction.practise1.ThreadUtils.delay;

public class _1_Shop {

    private String name;

    public _1_Shop(String name) {
        this.name = name;
    }

    private Random random = new Random();

    public double getPrice(String productName) {
        return calculatePrice(productName);
    }

    public Future<Double> getPriceAsync(String productName) {
        return CompletableFuture.supplyAsync(() -> {
            return calculatePrice(productName);
        });
    }

    public Future<Double> getPriceAsyncV1(String productName) {
        CompletableFuture<Double> price = new CompletableFuture<>();
        new Thread(
                () -> {
                    try {
                        if (true) throw new RuntimeException("product not available");
                        double productPrice = calculatePrice(productName);
                        price.complete(productPrice);
                    } catch (Exception e) {
                        price.completeExceptionally(e);
                        //e.printStackTrace();
                    }
                }
        ).start();
        return price;
    }

    private double calculatePrice(String productName) {
        delay(1);
        return random.nextDouble() * productName.charAt(0) + productName.charAt(1);
    }

    public String getName() {
        return name;
    }

}

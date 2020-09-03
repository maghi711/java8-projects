package com.aadavan.threads.nonblocking.iteration1._1;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Shop {

    private String name;
    static private Random random = new Random();

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void delay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    private double calculatePrice(String productName) {
        delay();
        return random.nextDouble() * productName.charAt(0) + productName.charAt(1);
    }

    public double getPrice(String productName) {
        return calculatePrice(productName);
    }

    public Future<Double> getPriceAsync(String productName) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(productName));
    }

    public Future<Double> getPriceAsyncOld(String productName) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(
                () -> {
                    try {
                        double price = calculatePrice(productName);
                        futurePrice.complete(price);
                    } catch (Exception e) {
                        futurePrice.completeExceptionally(e);
                    }
                }
        ).start();
        return futurePrice;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

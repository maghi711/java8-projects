package com.aadavan.check.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {

    public double getPrice(String productName) {
        return Double.valueOf(10000.00);
    }

    public Future<Double> getPriceAsync(String productName) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(
                () -> {
                    double price = getPrice(productName);
                    futurePrice.complete(price);
                }
        ).start();
        return futurePrice;
    }
}

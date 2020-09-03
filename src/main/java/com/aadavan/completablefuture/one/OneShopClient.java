package com.aadavan.completablefuture.one;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class OneShopClient {
    public static void main(String[] args) {
        OneShop shop = new OneShop("Amazon");
        long start = System.currentTimeMillis();
        Future<Double> future = shop.getPriceAsync("Nokia 8.3");
        long end = System.currentTimeMillis();
        System.out.println("(calling time) = " + (end - start) + " time taken.");
        try {
            System.out.println("Fetching the result");
            final Double aDouble = future.get(15, TimeUnit.SECONDS);
            System.out.printf("price returned is " + aDouble + ", after amount of time " + (System.currentTimeMillis() - start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}

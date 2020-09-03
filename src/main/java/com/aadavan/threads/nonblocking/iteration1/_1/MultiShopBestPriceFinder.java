package com.aadavan.threads.nonblocking.iteration1._1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class MultiShopBestPriceFinder {

    public static void main(String[] args) {
        withParallelStream_1(); // _1_ Call
        withAsyncRequest(); // _2_ call
    }

    private static void withAsyncRequest() {
        long s1 = System.currentTimeMillis();
        final List<String> prices = findPrices_v2("Nokia 8.3");
        long finishMillis = System.currentTimeMillis() - s1;
        System.out.println("finishMillis = " + finishMillis);
        System.out.println("prices = " + prices);
    }

    private static void withParallelStream_1() {
        long s1 = System.currentTimeMillis();
        final List<String> prices = findPrices_v1("Nokia 8.3");
        long finishMillis = System.currentTimeMillis() - s1;
        System.out.println("finishMillis = " + finishMillis);
        System.out.println("prices = " + prices);
    }

    public static List<String> findPrices_v1(String product) {
        System.out.println("Running findPrices_v1 product = " + product);
        List<Shop> shops = getShops();
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    public static List<String> findPrices_v2(String product) {
        System.out.println("Running findPrices_v2 product = " + product);
        List<Shop> shops = getShops();
        final List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))))
                .collect(Collectors.toList());
        return priceFutures.stream().map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    private static List<Shop> getShops() {
        return Arrays.asList(
                new Shop("Amazon"),
                new Shop("Flipkart"),
                new Shop("Croma"),
                new Shop("PaytmMall"),
                new Shop("Sapna")
        );
    }
}

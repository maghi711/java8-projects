package com.aadavan.lambdas.java8InAction.practise1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

import static com.aadavan.lambdas.java8InAction.practise1.ThreadUtils.delay;
import static java.util.stream.Collectors.toList;

public class _1_BestPriceFinderApplication {
    static List<_1_Shop> shops = Arrays.asList(
            new _1_Shop("Amazon"),
            new _1_Shop("Flipkart"),
            new _1_Shop("SapnaOnline"),
            new _1_Shop("Infibeam"));

    public static void main(String[] args) {
        //searchForAProductInAShop();
        searchForAProductInMultipleShops();
    }

    private static void searchForAProductInMultipleShops() {
        String productName = "Nokia 5.3";
        long startTime = System.currentTimeMillis();
        System.out.println(findPrices(productName));
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        System.out.println("timeTaken = " + timeTaken);
    }

    private static List<String> findPricesV1(String productName) {
        return shops.parallelStream().map(shop ->
                String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(productName))
        ).collect(toList());
    }

    private static List<String> findPrices(String productName) {
        List<CompletableFuture<String>> priceFeatures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getName() + " price is " + shop.getPrice(productName)
                )).collect(toList());
        return priceFeatures.stream().map(
                CompletableFuture::join
        ).collect(toList());
    }

    private static void searchForAProductInAShop() {
        final String shopName = "Amazon";
        _1_Shop shop = new _1_Shop(shopName);
        long startTime = System.currentTimeMillis();
        Future<Double> futurePrice = shop.getPriceAsync("Nokia 8.1");
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        System.out.println("Invocation time " + timeTaken);

        // Do some other operation
        doSomethingElse();

        // while the price of the product is being calculated
        try {
            double price = futurePrice.get(10, TimeUnit.SECONDS);
            System.out.printf("Price is %.2f%n", price);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        long retrievalTime = System.currentTimeMillis() - startTime;
        System.out.println("Price returned after " + retrievalTime + " milli seconds");
    }

    private static void doSomethingElse() {
        delay(2);
        System.out.println("Doing some other task.");
    }

}

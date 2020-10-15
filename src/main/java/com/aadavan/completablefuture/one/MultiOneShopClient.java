package com.aadavan.completablefuture.one;

import com.aadavan.completablefuture.two.TwoShop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class MultiOneShopClient {
    static List<OneShop> shops = buildShops("Amazon", "Flipkart", "Tata Click", "Sapna Online", "eBay", "Amazon", "Flipkart", "Tata Click", "Sapna Online");

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        long start = System.currentTimeMillis();
        //final List<String> prices = findPrices("Nokia8.3");
        final List<String> prices = findPricesCF("Nokia8.3");
        long end = System.currentTimeMillis();
        for (String price: prices) {
            System.out.println(price);
        }
        System.out.println("(end - start) = " + (end - start));

        start = System.currentTimeMillis();
        final List<String> prices1 = findPrices("Nokia8.3");
        end = System.currentTimeMillis();
        for (String price: prices1) {
            System.out.println(price);
        }
        System.out.println("(end - start) = " + (end - start));
    }

    static List<String> findPrices(String product) {
        return shops.parallelStream().map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    static List<String> findPricesCF(String product) {
        final List<CompletableFuture<String>> collect = shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " + shop.getPrice(product)))
                .collect(Collectors.toList());
        return collect.stream().map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    static OneShop buildShop(String shopName) {
        return new OneShop(shopName);
    }

    static List<OneShop> buildShops(String... shopName) {
        List<OneShop> shops = new ArrayList<>();
        for (String shop : shopName) {
            shops.add(new OneShop(shop));
        }
        return shops;
    }
}

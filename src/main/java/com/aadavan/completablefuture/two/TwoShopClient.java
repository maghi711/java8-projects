package com.aadavan.completablefuture.two;

import com.aadavan.completablefuture.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TwoShopClient {
    public static void main(String[] args) {
        final String shop = "Amazon";
        TwoShop twoShop = buildShop(shop);
        final String product = "Asus 1";
        System.out.println("Getting price of " + product + " from " + twoShop.getName());
        final Future<Double> priceAsync = twoShop.getPriceAsync(product);

        System.out.println("Doing some other operation");
        doSomeOtherOperation();
        try {
            final Double aDouble = priceAsync.get(5, TimeUnit.SECONDS);
            System.out.println("Got the price " + aDouble + " from the shop ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("Completed all the opeation");
    }

    static void doSomeOtherOperation() {
        ThreadUtils.delay(6, TimeUnit.SECONDS);
    }

    static TwoShop buildShop(String shopName) {
        return new TwoShop(shopName);
    }

    static List<TwoShop> buildShops(String... shopName) {
        List<TwoShop> shops = new ArrayList<>();
        for (String shop : shopName) {
            shops.add(new TwoShop(shop));
        }
        return shops;
    }
}

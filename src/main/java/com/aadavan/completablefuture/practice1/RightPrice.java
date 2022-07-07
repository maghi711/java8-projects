package com.aadavan.completablefuture.practice1;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RightPrice {

    private static final Logger LOGGER = LoggerFactory.getLogger(RightPrice.class);

    private static List<Shop> shops;

    public static void main(String[] args) {
        BasicConfigurator.configure();
        shops = getAllShops();
        LOGGER.info("shops = {}", shops);

        long start = System.nanoTime();
        LOGGER.info("Found Price: {}", findPrices("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        LOGGER.info("Done in {} msecs", duration);
    }

    private static List<Shop> getAllShops() {
        return Arrays.asList(
                new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll")
        );
    }

    public static List<String> findPrices(String product) {
        return shops
                .parallelStream()
                .map(
                    shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))
        ).collect(Collectors.toList());
    }
}

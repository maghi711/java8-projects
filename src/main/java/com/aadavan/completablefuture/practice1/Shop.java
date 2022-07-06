package com.aadavan.completablefuture.practice1;

import com.aadavan.completablefuture.utils.ThreadUtils;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.*;

public class Shop {

    private static final Logger LOGGER = LoggerFactory.getLogger(Shop.class);
    private static final Random random = new Random();

    private double calculatePrice(String product) {
        LOGGER.info("Calculating the price");
        ThreadUtils.delay(3, TimeUnit.SECONDS);
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public Future<Double> getPriceAsync(String product) {
        LOGGER.info("Running in a separate thread async");
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();
        LOGGER.info("Available processors are {}", Runtime.getRuntime().availableProcessors());
        Shop shop = new Shop();
        final String product = "Mi A1";
        final Future<Double> miA1 = shop.getPriceAsync(product);
        LOGGER.info("The calculated price from sync is {}", shop.calculatePrice(product));
        try {
            LOGGER.info("The calculated price from async is {}", miA1.get(15, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

}

package com.aadavan.completablefuture.practice1;

import com.aadavan.completablefuture.utils.ThreadUtils;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CFCheck {

    static {
        BasicConfigurator.configure();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CFCheck.class);

    public static void main(String[] args) throws Exception {
        final CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            LOGGER.info("The thread is {}", Thread.currentThread().getName());
            return number();
        });
        LOGGER.info("number() = {}", number());
        LOGGER.info("future.get() = {}", future.get());
    }
    
    static int number() {
        LOGGER.info("Working on the task");
        ThreadUtils.delay(10, TimeUnit.SECONDS);
        return 1;
    }
}

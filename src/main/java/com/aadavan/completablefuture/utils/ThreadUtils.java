package com.aadavan.completablefuture.utils;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.*;

public class ThreadUtils {

    public static void delay(long seconds, TimeUnit t) {
        try {
            t.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Usage: use the delay api as defined below
        ThreadUtils.delay(1, SECONDS);
    }
}

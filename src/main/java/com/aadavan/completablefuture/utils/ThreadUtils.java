package com.aadavan.completablefuture.utils;

import java.util.concurrent.TimeUnit;

public class ThreadUtils {

    public static void delay(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

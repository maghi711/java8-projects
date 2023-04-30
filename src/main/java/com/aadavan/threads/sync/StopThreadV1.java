package com.aadavan.threads.sync;

import java.util.concurrent.TimeUnit;

/**
 * This thread may or may not stop
 */
public class StopThreadV1 {

    private static boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested) {
                i++;
                System.out.println("i = " + i);
            }
        });
        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }
}

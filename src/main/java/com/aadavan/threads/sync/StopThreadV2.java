package com.aadavan.threads.sync;

import java.util.concurrent.TimeUnit;

public class StopThreadV2 {

    private static boolean stopRequested;

    private static synchronized void requestStop() {
        stopRequested = true;
    }

    private static synchronized boolean stopRequested() {
        return stopRequested;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread stopperThread = new Thread(
                () -> {
                    int i = 0;
                    while (!stopRequested()) {
                        i++;
                        System.out.println("i = " + i);
                    }
                }
        );
        stopperThread.start();
        TimeUnit.MILLISECONDS.sleep(50);
        requestStop();
    }
}

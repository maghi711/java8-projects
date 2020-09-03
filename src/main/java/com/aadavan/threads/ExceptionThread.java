package com.aadavan.threads;

import java.util.concurrent.TimeUnit;

public class ExceptionThread {
    public static void main(String[] args) {
        Runnable computeIntensiveOperation = () -> {
            System.out.println("Computing the string.");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String st = null;
            st.toString();
        };
        Thread thread = new Thread(computeIntensiveOperation);
        thread.setUncaughtExceptionHandler(new GlobalExceptionHandler());
        thread.start();
        System.out.println();
    }
}

package com.aadavan.threads;

public class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("In GlobalExceptionHandler of UnCaughtExceptionHandler " + t.getName() + e);
        e.printStackTrace();
    }
}

package com.aadavan.lambdas.hello;

public class _1_HelloLambda {
    public static void main(String[] args) {
        Runnable r = () -> {
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        };
        new Thread(r).start();
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
    }
}

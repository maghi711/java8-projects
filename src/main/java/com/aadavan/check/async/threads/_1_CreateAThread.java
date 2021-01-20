package com.aadavan.check.async.threads;

public class _1_CreateAThread {
    public static void main(String[] args) {
        Runnable task = () -> {
            System.out.println("Running in " + Thread.currentThread().getName());
        };
        Thread t = new Thread(task);
        t.setName("My New Thread");
        t.start(); // launches the thread in the new thread
        t.run();  // launches the thread in the same main thread
    }
}

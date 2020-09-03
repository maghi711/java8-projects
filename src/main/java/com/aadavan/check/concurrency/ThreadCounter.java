package com.aadavan.check.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadCounter {
    private AtomicInteger c = new AtomicInteger(0);

    public void increment() {
        c.incrementAndGet();
    }

    public void decrement() {
        c.decrementAndGet();
    }

    public int value() {
        return c.get();
    }

    public static void main(String[] args) {
        ThreadCounter tc = new ThreadCounter();
        Thread t1 = new Thread(() -> {
            tc.increment();
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            tc.increment();
        });
        t2.start();
        Thread t3 = new Thread(() -> {
            tc.increment();
        });
        t3.start();
        try {
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("tc = " + tc.value());
        System.out.println();
    }
}

package com.aadavan.check.async.threads;

public class RaceCondition {
    public static void main(String[] args) throws InterruptedException {
        //initCheck();
        multipleThreadsCheck();
    }

    private static void multipleThreadsCheck() throws InterruptedException {
        LongWrapper wrapper = new LongWrapper(0l);
        Runnable task = () -> {
            for (int i = 0; i < 1_000; i++) {
                wrapper.incrementByOne();
            }
        };

        Thread[] threads = new Thread[1_000];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(task);
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        System.out.println(wrapper.geLongValue());
    }

    private static void singleThread() throws InterruptedException {
        LongWrapper wrapper = new LongWrapper(0l);
        Runnable task = () -> {
            for (int i = 0; i < 1_000; i++) {
                wrapper.incrementByOne();
            }
        };
        Thread t = new Thread(task);
        t.start();
        t.join();
        System.out.println(wrapper.geLongValue());
    }
}

package com.aadavan.threads.completablefuture;

import java.util.concurrent.*;

public class _1_ExecutorPatternTaskExecution {

    public static void main(String[] args) {
        System.out.println("Started main thread");
        Runnable task = () ->
        {
            sleep(5);
            System.out.println("The wishing message is Hello aadavan");
        };
        ExecutorService service = Executors.newCachedThreadPool();
        Future<?> future = service.submit(task);
        boolean done = true;
        while (done) {
            System.out.println("done = " + done);
            if (future.isDone()) {
                done = false;
                try {
                    Object o = future.get();
                    System.out.println("o = " + o);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
        service.shutdown();
    }

    private static void sleep(long sleepTime) {
        try {
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

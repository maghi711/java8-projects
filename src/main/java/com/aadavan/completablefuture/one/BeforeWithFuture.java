package com.aadavan.completablefuture.one;

import java.util.concurrent.*;

public class BeforeWithFuture {

    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newCachedThreadPool();
        final Future<Double> future = executorService.submit(() -> {
                    return doSomeLongComputation();
                }
        );
        doSomethingElse();
        Double aDouble=0.0;
        try {
            aDouble = future.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("aDouble = " + aDouble);
        executorService.shutdown();
    }

    private static void doSomethingElse() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Doing something else");
    }

    private static Double doSomeLongComputation() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 2.0;
    }
}

package com.aadavan.lambdas.java8InAction.practise1;

import java.util.concurrent.*;

public class _1_LongLastingOperation {
    public static void main(String[] args) {
        System.out.println("Long lasting operation STARTED.");
        ExecutorService service = Executors.newCachedThreadPool();
        final Future<Double> futureResult = service.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return doSomeLongRunningOperation();
            }
        });
        doSomeOtherTask();
        try {
            Double result = futureResult.get(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("Long lasting operation COMPLETED.");
        service.shutdown();
    }

    private static void doSomeOtherTask() {
        System.out.println("Performing some other task STARTED.");
        sleep(5);
        System.out.println("Performing some other task COMPLETED.");
    }

    private static Double doSomeLongRunningOperation() {
        System.out.println("Performing ASYNC task STARTED.");
        sleep(2);
        System.out.println("Performing ASYNC task COMPLETED.");
        return 0.0;
    }

    private static void sleep(long timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

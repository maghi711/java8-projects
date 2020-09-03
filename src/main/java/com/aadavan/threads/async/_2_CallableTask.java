package com.aadavan.threads.async;

import java.util.concurrent.*;

public class _2_CallableTask {
    public static void main(String[] args) {
        Callable<Void> taskWithReturn = () -> {
            System.out.println("Task with Return");
            return null;
        };
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        final Future<?> callableTask = executorService.submit(taskWithReturn);
        try {
            final Object o = callableTask.get();
            System.out.println("o = " + o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

package com.aadavan.check.async.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class _Runnable_ExecutorService_Pattern {
    public static void main(String[] args) {
        //_1_threadCreation_ClassicalPattern();
        //_2_threadCreation_ExecutorServicePatterns();
        _3_threadCreation_CallableServicePattern();
    }

    public static void _1_threadCreation_ClassicalPattern() {
        final Runnable runnableTask = getRunnableTask();
        for (int i = 0; i < 10; i++) {
            // for testing only one thread use 1 instead of 10 in the loop
            new Thread(runnableTask).start();
        }
    }

    public static void _2_threadCreation_ExecutorServicePatterns() {
        final Runnable runnableTask = getRunnableTask();
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            service.submit(runnableTask);
        }
        service.shutdown();
    }

    private static void _3_threadCreation_CallableServicePattern() {
        final Callable<String> callableTask = getCallableTask();
        ExecutorService service = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 100; i++) {
                service.submit(callableTask);
            }
        } finally {
            service.shutdown();
        }
    }

    private static Callable<String> getCallableTask() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (true) throw new NullPointerException("Can't be null");
        return () -> "I am running in " + Thread.currentThread().getName();
    }

    public static Runnable getRunnableTask() {
        Runnable task = () -> System.out.println("I am running in a thread with name : " + Thread.currentThread().getName());
        return task;
    }

}

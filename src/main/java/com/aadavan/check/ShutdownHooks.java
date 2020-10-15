package com.aadavan.check;

import com.aadavan.completablefuture.utils.ThreadUtils;

import java.util.concurrent.TimeUnit;

public class ShutdownHooks {
    public static void main(String[] args) {
        registerShutdownHooks();
        doSomeOtherThing();
    }

    private static void doSomeOtherThing() {
        System.out.println("fetch some data");
        try {
            TimeUnit.MINUTES.sleep(5);
        } catch (InterruptedException e) {
            System.out.println("Some exception " + e.getMessage());
        }
    }

    private static void registerShutdownHooks() {
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> System.out.println("Shutdown Hook is running !"))
        );
    }
}

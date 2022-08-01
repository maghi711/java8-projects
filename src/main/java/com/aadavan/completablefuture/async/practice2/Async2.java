package com.aadavan.completablefuture.async.practice2;

import com.aadavan.completablefuture.utils.ThreadUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Async2 {
    public static void main(String[] args) throws Exception {
        Greeting greeting = new Greeting();
        final String name = "Aadavan";
        greeting.greet(name);
        final Future<String> stringFuture = greeting.greetAsync(name);
        stringFuture.get();
    }
}

class Greeting {
    public String greet(String name) {
        final String result = "\tHello " + name;
        System.out.println(getCurrentThreadName() + result);
        return result;
    }

    public Future<String> greetAsync(String name) {
        ThreadUtils.delay(2, TimeUnit.SECONDS);
        return CompletableFuture.supplyAsync(() -> greet(name));
    }

    public static String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }
}

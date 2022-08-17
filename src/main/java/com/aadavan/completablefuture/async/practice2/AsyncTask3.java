package com.aadavan.completablefuture.async.practice2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsyncTask3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Greeter<String, String> nameGreeter = new NameGreeter();
        final String aadavan = nameGreeter.greet("Aadavan");
        System.out.println("aadavan = " + aadavan);

        final Future<String> aadavan1 = nameGreeter.asyncGreeter("Aadavan");
        System.out.println("aadavan1.get() = " + aadavan1.get());

        Greeter<Void, String> defaultGreeter = new VoidGreeter();
        final String stringFuture = defaultGreeter.greet(null);
        System.out.println("stringFuture = " + stringFuture);
    }
}

interface Greeter<U, V> {
    V greet(U u);

    Future<V> asyncGreeter(U u);
}

class VoidGreeter implements Greeter<Void, String> {

    @Override
    public String greet(Void unused) {
        return "Hello World";
    }

    @Override
    public Future<String> asyncGreeter(Void unused) {
        return null;
    }
}

class NameGreeter implements Greeter<String, String> {
    @Override
    public String greet(String s) {
        printCurrentThread();
        return "Hello " + s + " welcome to our universe.";
    }

    @Override
    public Future<String> asyncGreeter(String s) {
        printCurrentThread();
        return CompletableFuture.supplyAsync(() -> greet(s));
    }

    private void printCurrentThread() {
        System.out.println("Running in thread " + Thread.currentThread().getName());
    }
}

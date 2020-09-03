package com.aadavan.threads.async.practise_1;

import java.util.concurrent.CompletableFuture;

public class _1AsyncTask {
    public static void main(String[] args) throws Exception {
        CompletableFuture.runAsync(() -> System.out.println("Hello World"));
        Thread.sleep(1);
    }
}

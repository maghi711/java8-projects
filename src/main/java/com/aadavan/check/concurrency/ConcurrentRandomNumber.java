package com.aadavan.check.concurrency;

import java.util.concurrent.ThreadLocalRandom;

public class ConcurrentRandomNumber {

    public static void main(String[] args) {
        ThreadLocalRandom localRandom = ThreadLocalRandom.current();
        for (int i = 0; i < 10; i++) {
            int localInt = localRandom.nextInt();
            System.out.println(localInt);
        }
    }
}

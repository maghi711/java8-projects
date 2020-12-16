package com.aadavan.check.async;

import com.couchbase.client.core.time.Delay;
import com.couchbase.client.java.util.retry.RetryBuilder;
import rx.Observable;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CouchbaseAsyncCheck {
    public static void main(String[] args) {
        //zeroApproach();
        //firstAppraoch();
        secondApproach();
    }

    private static void zeroApproach() {
        Observable
                .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .doOnNext(integer -> {
                    System.out.println(integer);
                    final int i = new Random().nextInt(10);
                    System.out.println("i = " + i);
                    //if (i + 1 == 5) {
                        throw new RuntimeException("Boo!");
                    //}
                })
                .retry()
                .distinct()
                .subscribe(integer -> {
                    System.out.println(integer + "\n\n");
                });
    }

    private static void secondApproach() {
        Observable.range(1, 10)
                .doOnNext(integer -> {
                    if (integer == 5) {
                        throw new RuntimeException("Boo!");
                    }
                })
                .retryWhen(
                        RetryBuilder
                                .anyOf(RuntimeException.class)
                                .delay(Delay.exponential(TimeUnit.SECONDS, 100))
                                .max(5)
                                .build()
                )
                .subscribe(System.out::println);
    }

    private static void firstAppraoch() {
        Observable
                .range(1, 10)
                .doOnNext(integer -> {
                    if (integer == 5) {
                        throw new RuntimeException("Boo!");
                    }
                })
                .retryWhen(attempts ->
                        attempts.zipWith(Observable.range(1, 3), (n, i) -> i)
                                .flatMap(i -> {
                                    System.out.println("delay retry by " + i + " second(s)");
                                    return Observable.timer(i, TimeUnit.SECONDS);
                                }))
                .distinct()
                .subscribe(System.out::println);
    }
}

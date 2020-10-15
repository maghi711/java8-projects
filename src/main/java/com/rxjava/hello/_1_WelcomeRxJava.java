package com.rxjava.hello;

import rx.Observable;

public class _1_WelcomeRxJava {
    public static void main(String[] args) {
        final String message = "Welcome Aadavan to RxJava";
        String result=null;
        Observable<String> observable = Observable.just(message);
        observable.subscribe(
            s -> {
                System.out.println(s);
            });
    }
}

package com.aadavan.completablefuture.practice3;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CheckGenerics {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("ABC", "BCD", "DEF");
        print(names);
        List<Date> dates = Arrays.asList(new Date(), new Date());
        print(dates);
        addAnyObject(dates, new Date());
        print(dates);
    }

    static void print(List<? extends Object> stores) {
        System.out.println(stores);
    }

    static <T> void addAnyObject(List<? super Date> stores, T value) {
        stores.add(new Date());
    }
}

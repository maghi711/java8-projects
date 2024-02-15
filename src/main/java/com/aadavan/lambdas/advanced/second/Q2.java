package com.aadavan.lambdas.advanced.second;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Q2 {
    public static void main(String[] args) {
        ThreadLocal<DateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd-MMM-yyyy"));
        final String date = formatter.get().format(new Date());
        System.out.println(date);
    }
}

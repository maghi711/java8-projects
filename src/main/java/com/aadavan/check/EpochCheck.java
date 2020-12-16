package com.aadavan.check;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class EpochCheck {

    final private static DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS");

    public static void main(String[] args) {
        int historyTTL = 6  * 30 * 24 * 60 * 60;
        System.out.println("historyTTL = " + historyTTL);
        System.out.println("historyTTL date = " + dateFormat.format(new Date(historyTTL)));
        final long currentSecond = Instant.now().getEpochSecond();
        System.out.println("currentSecond = " + currentSecond);
        long finalTtl = currentSecond + historyTTL;
        System.out.println("Final TTL  = " + finalTtl);

        long ttl = 1605202431;
        System.out.println("TTL date = " + dateFormat.format(new Date(ttl)));
        System.out.println("Final TTL Date " + dateFormat.format(new Date(finalTtl)));
        System.out.println("Current date " + dateFormat.format(new Date((System.currentTimeMillis()))));
        System.out.println("Current date " + dateFormat.format(new Date((System.currentTimeMillis() + historyTTL))));
    }
}

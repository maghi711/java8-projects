package com.aadavan.check.time;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

public class TimeDifference {
    static final SimpleDateFormat milliSecondFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss,SSS");
    public static void main(String[] args) throws Exception {
        usingDateToMillis();
    }
    static void usingLocalDateTime() throws Exception {
        //LocalDateTime previous = LocalDateTime.parse("2020-06-04 17:16:59,86");
        LocalDateTime previous = LocalDateTime.of(2020, Month.JUNE, 04, 17,16,59,643);

        long prevMillis = previous.getNano();
        ZonedDateTime previousZdt = ZonedDateTime.of(previous, ZoneId.systemDefault());
        //LocalDateTime latest = LocalDateTime.parse("2020-06-04 17:17:01,511");
        LocalDateTime latest = LocalDateTime.of(2020, Month.JUNE, 04, 17,17,01,999);
        ZonedDateTime latestZdt = ZonedDateTime.of(latest, ZoneId.systemDefault());
        System.out.println("latestZdt = " + latestZdt.toInstant().toEpochMilli());
        System.out.println("previousZdt = " + previousZdt.toInstant().toEpochMilli());
        System.out.println("System.currentTimeMillis() = " + System.currentTimeMillis());
        final long seconds = latestZdt.toInstant().toEpochMilli() - previousZdt.toInstant().toEpochMilli();
        System.out.println("seconds = " + seconds);
    }
    static void usingDateToMillis() throws Exception {
        String oldStr = "2020-06-04 17:16:59,864";
        //String oldStr = "2020-06-04 17:16:59,875";
        //String newStr = "2020-06-04 17:17:01,511";
        String newStr = "2020-06-04 17:17:01,485";
        String updateStr = "2020-06-04 17:17:02,521";
        final Date txnStartTime = milliSecondFormat.parse(oldStr);
        final Date txnResposeCheckTime = milliSecondFormat.parse(newStr);
        final Date txnUpdateRequestTime = milliSecondFormat.parse(updateStr);

        Calendar cal = Calendar.getInstance();
        cal.setTime(txnStartTime);
        final long oldTimeMillis = cal.getTimeInMillis();
        System.out.println("txnStartTime = " + oldTimeMillis);

        cal.setTime(txnResposeCheckTime);
        long newTimeMillis = cal.getTimeInMillis();
        System.out.println("txnResposeCheckTime = " + newTimeMillis);
        System.out.println("(newTimeMillis - oldTimeMillis = " + (newTimeMillis - oldTimeMillis));

        cal.setTime(txnUpdateRequestTime);
        long updateTimeMillis = cal.getTimeInMillis();
        newTimeMillis = cal.getTimeInMillis();
        System.out.println("(updateTimeMillis - oldTimeMillis = " + (updateTimeMillis - oldTimeMillis));
    }
}

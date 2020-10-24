package com.aadavan.check.basic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeCheck {
    public static void main(String[] args) {
        ZonedDateTime dateTime = ZonedDateTime.now();
        System.out.println("dateTime = " + dateTime);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        String text = dateTime.format(formatter);
        System.out.println("text = " + text);
        ZonedDateTime parsedDate = ZonedDateTime.parse(text, formatter);
        System.out.println("parsedDate = " + parsedDate);
    }
}

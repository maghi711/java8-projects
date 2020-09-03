package com.aadavan.trident.qa;

import java.util.Arrays;
import java.util.List;

/**
 * java.lang.NumberFormatException: For input string:"4.0"
 * fix.
 */
public class ParseLongCheck {
    public static void main(String[] args) {
        List<String> values = Arrays.asList("1", "4.0   ", "20000 ", "10 ", " 1.0", "2.0", "-1");
        //String purchaseAmount = "4.0";
        for (String purchaseAmount: values) {
            //final long result = Long.parseLong(purchaseAmount);
            //final long result = Double.valueOf(purchaseAmount).longValue();
            final long result = (long) Double.parseDouble(purchaseAmount); // Fix for the NumberFormatException
            System.out.println("for [" + purchaseAmount + "] the long value is = " + result);
        }
    }
}

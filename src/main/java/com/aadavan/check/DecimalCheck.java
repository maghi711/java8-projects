package com.aadavan.check;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DecimalCheck {

    public static void main(String[] args) {
        //testINR();// 2
        System.out.println("\n\n\n");
        //testVND();// 0
        System.out.println("\n\n\n");
        //testKWD();//3
        //testOthers();//3

        //testNumber();
        testLogic2();
    }

    private static void testLogic2() {
        BigDecimal baseExponent = new BigDecimal(100);
        BigDecimal baseCurrencyDiff = new BigDecimal("70.394");
        BigDecimal amnt = new BigDecimal(99.99);
        //double amt = Double.parseDouble(df.format(amnt / baseCurrencyDiff)) * baseExponent;
        BigDecimal divide = amnt.divide(baseCurrencyDiff,2, RoundingMode.HALF_UP);
        final BigDecimal newValue = divide.setScale(Integer.parseInt("2"), BigDecimal.ROUND_HALF_UP);
        final BigDecimal tempRes = newValue.multiply(baseExponent);
        System.out.println(tempRes.longValue());
    }

    private static void testNumber() {
        BigDecimal value = new BigDecimal(99.99);
        BigDecimal multiply = value.multiply(new BigDecimal("70.394"));
        multiply = multiply.setScale(Integer.parseInt("2"), BigDecimal.ROUND_HALF_UP);
        BigDecimal tmpRes = multiply.multiply(new BigDecimal(100));
        //tmpRes.setScale(Integer.parseInt(baseCurrencyExponent), BigDecimal.ROUND_HALF_UP);
        System.out.println(tmpRes.longValue());
    }

    private static void testINR() {
        //System.out.println(logic(1234, 227.583067, 3, 0)); // INR, 2
        //System.out.println(logic(1000, 227.583067, 3, 0)); // USD, 2
        //System.out.println(logic(1000000, 0.0590923513, 3, 0));

        System.out.println(logic(1000, 1, 2, 2));
        System.out.println(logic(1000, 70.394, 2, 2));
        System.out.println();
        System.out.println(logic(87758, 0.002962, 0, 2));
        System.out.println(logic(100, 0.645769117, 0, 2));
        System.out.println();
        System.out.println(logic(1000, 227.583067, 3, 2));
        System.out.println(logic(1000000, 0.0590923513, 3, 2));
        System.out.println();
        // Uruguvay to USD check
        //System.out.println(logic(10000000, 1.963737, 2, 2));
        //System.out.println(logic(19637370, 0.014477, 2, 2));
    }

    private static void testVND() {
        System.out.println(logic(26000, 337.529208, 2, 0));
        System.out.println(logic(70394, 337.529208, 2, 0));

        System.out.println(logic(1000000, 1, 0, 0));
        System.out.println(logic(6458, 337.529208, 2, 0));

        System.out.println(logic(22758, 337.529208, 2, 0));
        System.out.println(logic(5909, 337.529208, 2, 0));
    }

    private static void testKWD() {
        System.out.println(logic(1000, 227.583067, 2, 3));
        System.out.println(logic(70394, 227.583067, 2, 3));
        System.out.println(logic(25994, 227.583067, 2, 3));//87758
        System.out.println(logic(6458, 227.583067, 2, 3));//100
        System.out.println(logic(22758, 227.583067, 2, 3));//1000
        System.out.println(logic(5909, 227.583067, 2, 3));//1000000 IQD
    }

    private static void testOthers() {
       //System.out.println(logic(2235, 227.583067, 3, 2));
        //System.out.println(logic(9999, 70.394, 2, 2));
        System.out.println(logic(7039, 0.014477, 2, 2));
    }

    static String logic(long value, double currencyRate, int sourceExponent, int destExponent) {
        BigDecimal decimal = new BigDecimal(value);
        final BigDecimal multiply = decimal.multiply(new BigDecimal(currencyRate));
        System.out.println("multiply = " + multiply);
        final BigDecimal divide = multiply.divide(new BigDecimal(getMultiplicationFactor(sourceExponent)));
        System.out.println("divide = " + divide);
        final BigDecimal round = divide.setScale(destExponent, BigDecimal.ROUND_HALF_UP);
        System.out.println("round = " + round);
        return round.toString();
    }

    private static int getMultiplicationFactor(int sourceExponent) {
        switch (sourceExponent) {
            case 0: return 1;
            case 1: return 10;
            case 2: return 100;
            case 3: return 1000;
            case 4: return 10000;
        }
        return 1;
    }
}













package com.aadavan.check.async;

public class BestProductPriceFinder {

    public static void main(String[] args) throws Exception {
        Shop shop = new Shop();
        String productName = "MiA1";
        System.out.println(productName + " best price is : " + shop.getPriceAsync(productName).get());
    }
}

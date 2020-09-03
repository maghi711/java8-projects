package com.aadavan.threads.nonblocking.iteration1._1;

public class BestProductPriceFinder {

    public static void main(String[] args) throws Exception {
        Shop shop = new Shop("paytmmall");
        String productName = "MiA1";
        System.out.println(productName + " best price is : " + shop.getPriceAsync(productName).get());
    }
}

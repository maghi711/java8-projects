package com.aadavan.check.core.intf;

public class CardApplication {
    public static void main(String[] args) {
        CardIntf card = new CCardImpl();
        System.out.println("card.getName() = " + card.getName());
    }
}

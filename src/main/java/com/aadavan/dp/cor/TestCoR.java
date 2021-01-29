package com.aadavan.dp.cor;

import com.aadavan.dp.cor.operations.*;

public class TestCoR {
    public static void main(String[] args) {
        Chain c1 = new AddNumbers();
        Chain c2 = new SubtractNumbers();
        Chain c3 = new MultiplyNumbers();
        Chain c4 = new DivideNumbers();

        c1.setNextChain(c2);;
        c2.setNextChain(c3);
        c3.setNextChain(c4);

        for (Operation operation: Operation.values()) {
            Numbers request = new Numbers(4, 2, operation);
            c1.calculate(request);
        }
    }
}

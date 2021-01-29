package com.aadavan.dp.cor.operations;

public class MultiplyNumbers  extends AbstractChain {
    @Override
    public void calculate(Numbers request) {
        if (request.getOperation().equals(Operation.MULTIPLY)) {
            System.out.println(request.getN1() + " * " + request.getN2() + " = " +
                    (request.getN1() * request.getN2()));
        } else {
            forward(request);
        }
    }

}

package com.aadavan.dp.cor.operations;

public class DivideNumbers extends AbstractChain {
    @Override
    public void calculate(Numbers request) {
        if (request.getOperation().equals(Operation.DIVIDE)) {
            System.out.println(request.getN1() + " / " + request.getN2() + " = " +
                    (request.getN1() / request.getN2()));
        } else {
            forward(request);
        }
    }
}

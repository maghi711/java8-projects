package com.aadavan.dp.cor.operations;

public abstract class AbstractChain implements Chain {
    protected Chain nextChain;

    @Override
    public void setNextChain(Chain nextChain) {
        this.nextChain = nextChain;
    }

    protected void forward(Numbers request) {
        nextChain.calculate(request);
    }
}

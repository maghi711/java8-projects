package com.aadavan.dp.cor.operations;

public interface Chain {
    void setNextChain(Chain nextChain);
    void calculate(Numbers request);
}

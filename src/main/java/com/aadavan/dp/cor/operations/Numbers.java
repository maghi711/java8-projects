package com.aadavan.dp.cor.operations;

import com.aadavan.dp.cor.operations.Operation;

public class Numbers {
    private int n1;
    private int n2;
    private Operation operation;
    public Numbers(int i, int j, Operation operation) {
        this.n1 = i;
        this.n2 = j;
        this.operation = operation;
    }

    public int getN1() {
        return n1;
    }

    public int getN2() {
        return n2;
    }

    public Operation getOperation() {
        return operation;
    }
}

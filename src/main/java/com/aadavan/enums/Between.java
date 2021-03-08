package com.aadavan.enums;

public class Between implements Operator {
    @Override
    public boolean operator(int total, int left, int right) {
        return total > left && total < right;
    }
}

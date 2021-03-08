package com.aadavan.enums;

public enum OperatorType {
    LESS_THAN("<"),
    GREATER_THAN(">"),
    LESS_THAN_EQUALS(">="),
    GREATER_THAN_EQUALS(">="),
    BETWEEN("><");

    private final String operator;
    OperatorType(String operator) {
        this.operator = operator;
    }

    private String operator() {
        return operator;
    }

    public static void main(String[] args) {
        final OperatorType[] values = OperatorType.values();
        for (OperatorType type: values) {
            System.out.println(type + " " + type.operator());
        }
    }
}

package com.aadavan.enums;

public class OperatorFactory {
    static Operator getOperator(OperatorType operatorType) {
        switch(operatorType) {
            case LESS_THAN:
                return new LessThan();
            case LESS_THAN_EQUALS:
                return new LessThanEquals();
            case GREATER_THAN:
                return new GreaterThan();
            case GREATER_THAN_EQUALS:
                return new GreaterThanEquals();
            case BETWEEN:
                return new Between();
            default:
                throw new RuntimeException("Unknown operation");
        }
    }

    public static void main(String[] args) {
        final Operator operator = OperatorFactory.getOperator(OperatorType.LESS_THAN);
        System.out.println(operator);
    }
}

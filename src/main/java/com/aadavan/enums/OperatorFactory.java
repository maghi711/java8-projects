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
            default:
                throw new RuntimeException("Unknown operation");
        }
    }
}

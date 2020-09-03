package com.aadavan.rule.execution.quickrule.check;

public class _2_Check {
    public static void main(String[] args) {

        String beforeOperand = "cardNo";
        String afterOperand = "'cardNo'|'2500000'|'5'";
        String operand = "SUM_AMOUNT_IN_TIME_SUCCESS_PRIMARY_ID_MIN";

        Expression exp = new Expression();
        exp.setLeftHandValue(beforeOperand);
        exp.setRightHandValue(afterOperand);
        exp.setOperator(operand);

        String operation = exp.getExpression();
        System.out.println(operation);

/*        QuickRuleExecutor quickRuleExecutor = new QuickRuleExecutor();
        quickRuleExecutor.exp = exp;
        quickRuleExecutor.operation = operation;
        quickRuleExecutor.word = operand;*/

    }
}

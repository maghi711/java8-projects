package com.aadavan.rule.execution.quickrule.check;

import com.google.common.base.Objects;

import java.util.List;

public class Expression {

    String leftHandValue;
    String rightHandValue;
    String operator;
    List parameters;

	/*public Expression(String beforeOperand, String operand, String afterOperand) {
		this.leftHandValue = beforeOperand;
		this.rightHandValue = afterOperand;
		this.operator = operand;
	}*/

    public String getExpression(){
        StringBuilder sb = new StringBuilder();

        leftHandValue = leftHandValue.replaceAll("'", "");
        rightHandValue = rightHandValue.replaceAll("'", "");

        if(GeneralUtility.isNumeric(leftHandValue) == false || GeneralUtility.isNumeric(rightHandValue)){
            leftHandValue = "'"+leftHandValue+"'";
            rightHandValue = "'"+rightHandValue+"'";
        }

        if(operator.equals("=")){
            operator = "==";
        }

        return leftHandValue+" "+operator+" "+rightHandValue;
    }

    public List getParameters() {
        return parameters;
    }
    public void setParameters(List parameters) {
        //System.out.println("Settin param in Exp:"+parameters);
        this.parameters = parameters;
    }


    public String getLeftHandValue() {
        return leftHandValue;
    }
    public void setLeftHandValue(String leftHandValue) {
        this.leftHandValue = leftHandValue;
    }
    public String getRightHandValue() {
        return rightHandValue;
    }
    public void setRightHandValue(String rightHandValue) {
        this.rightHandValue = rightHandValue;
    }
    public String getOperator() {
        return operator;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expression that = (Expression) o;
        return Objects.equal(leftHandValue, that.leftHandValue) &&
                Objects.equal(rightHandValue, that.rightHandValue) &&
                Objects.equal(operator, that.operator) &&
                Objects.equal(parameters, that.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(leftHandValue, rightHandValue, operator, parameters);
    }

    @Override
    public String toString() {
        return "Expression{" +
                "leftHandValue='" + leftHandValue + '\'' +
                ", rightHandValue='" + rightHandValue + '\'' +
                ", operator='" + operator + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}

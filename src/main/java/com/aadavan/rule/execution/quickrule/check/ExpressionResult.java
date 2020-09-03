package com.aadavan.rule.execution.quickrule.check;

import java.io.Serializable;

public class ExpressionResult implements Serializable {
	String result;
	String analyzedData;
	FunctionArguments functionArgument;
	String log;

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public FunctionArguments getFunctionArgument() {
		return functionArgument;
	}
	public void setFunctionArgument(FunctionArguments functionArgument) {
		this.functionArgument = functionArgument;
	}
	public String getResult() {
		return result;
	}
	public String getAnalyzedData() {
		return analyzedData;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public void setAnalyzedData(String analyzedData) {
		this.analyzedData = analyzedData;
	}
}

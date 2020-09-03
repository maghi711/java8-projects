package com.aadavan.rule.execution.quickrule.check;

import java.io.Serializable;
import java.util.logging.Logger;

import static jeval.EvaluationConstants.FUNCTION_ARGUMENT_SEPARATOR;

public class FunctionArguments implements Serializable {
	private static final Logger logger = Logger.getLogger(FunctionArguments.class.getName());
	static boolean QUICK_RULE_ENCODE_SPECIAL_CHAR_ENABLED = false;
	String ruleId;	
	String operand;
	String subject;
	String rawParamName;	
	String rawFunctionName;	
	String implementationFunctionName;
	String cardNo;	
	String uid;
	String ruleSubject;
	String clientId;
	String paramName;
	
	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getUid() {
		return uid;
	}

	public String getRuleSubject() {
		return ruleSubject;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public void setRuleSubject(String ruleSubject) {
		this.ruleSubject = ruleSubject;
	}
	
	public String getRuleId() {
		if(ruleId != null){
			ruleId = ruleId.replace("'", "");	
		}		
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getClientId() {
		clientId = clientId.replace("'", "");
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getOperand() {
		return operand;
	}

	public void setOperand(String operand) {
		this.operand = operand;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getRawParamName() {
		return rawParamName;
	}

	public void setRawParamName(String rawParamName) {
		this.rawParamName = rawParamName;
	}

	public String getRawFunctionName() {
		return rawFunctionName;
	}

	public void setRawFunctionName(String rawFunctionName) {
		this.rawFunctionName = rawFunctionName;
	}	
	
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getImplementationFunctionName() {
		return implementationFunctionName;
	}

	public void setImplementationFunctionName(String implementationFunctionName) {
		this.implementationFunctionName = implementationFunctionName;
	}

	public static FunctionArguments getFunctionArguments(String argumentString){
		if("true".equals(QUICK_RULE_ENCODE_SPECIAL_CHAR_ENABLED)){
			argumentString = FunctionArguments.decodeSpecialChar(argumentString);
		}		
		if("true".equals("true")){
			logger.info("Actual arg1 :"+argumentString);
		}
		FunctionArguments fa = new FunctionArguments();
		String operand;
		String subject;
		String rawParamName;
		int indexOfRawArgument = 0;		
		
		if(argumentString == null){
			return null;
		}		
		
		if( argumentString.contains("~~~")){
			String tem = argumentString.substring(argumentString.indexOf("~~~"),argumentString.length());
		
			String args[] = tem.split("~~~", -1);
			
			rawParamName = args[1];
			fa.setRawFunctionName( args[2]) ;
			
			if(args.length >= 3){			
				fa.setCardNo( args[3]) ;
			}
			
			if(args.length >= 4){	
				String clientId = args[4];
				clientId = clientId.replace("'", "");
				fa.setClientId( args[4]) ;
			}
			
			if(args.length >= 5){	
				String ruleId = args[5];				
				fa.setRuleId( args[5]) ;
			}
			
			if(args.length >= 6){	
				String uid = args[6];				
				fa.setUid(uid) ;
			}
			
			if(args.length >= 7){	
				String ruleSubject = args[7];				
				fa.setRuleSubject(ruleSubject) ;
			}
			
			if(args.length >= 8){	
				String paramName = args[8];				
				fa.setParamName(paramName) ;
			}
			
		}else{
			rawParamName = "";
			fa.setRawFunctionName("");
		}
		if("true".equals("true")){
			logger.info("argumentString :"+argumentString);
		}
		String[] argList = argumentString.split("','" ,-1);	
		operand = argList[0];
		subject = argList[1];
		
		/*
		if(operand != null && operand.contains("#COMMA#")){
			operand = operand.replace("#COMMA#", ",");
		}
		
		if(subject != null && subject.contains("#COMMA#")){
			subject = subject.replace("#COMMA#", ",");
		}
		*/
		
		indexOfRawArgument = subject.indexOf("~~~");
		subject = subject.substring(0, indexOfRawArgument);
		
		operand = operand.replace("'", "");
		subject = subject.replace("'","");
		
		fa.setOperand(operand);		
		fa.setRawParamName(rawParamName);
		fa.setSubject(subject);
		if("true".equals("true")){
			logger.info("Raw getRawFunctionName:"+fa.getRawFunctionName());
			logger.info("Raw getRawParamName:"+fa.getRawParamName());
			logger.info("operand:"+operand);
			logger.info("subject:"+subject);
		}
		
		return fa;	
	}
	
	public String getFunctionExpression(){
		//
		String rawArgument = getRawParamName();
		String functionNameFromUI = getRawFunctionName();
		String functionArgument1 = getOperand();
		String functionArgument2 = getSubject();
		String functionName = getImplementationFunctionName();
		String cardno = getCardNo();
		String clientId = getClientId();
		String ruleId =getRuleId();
		String uid = getUid();
		String ruleSubject = getRuleSubject();
	 
	    
	    StringBuilder sb = new StringBuilder();

	    rawArgument = rawArgument.replace("{", "[");
	    rawArgument = rawArgument.replace("}", "]");
	   
	    functionArgument2 = functionArgument2.replace("'", "");
	    
	    sb.append(functionName).append("");
	    sb.append("(");
	    
    	sb.append(" ");
    		sb.append("'").append(functionArgument1.replaceAll("'", "")).append("'");
            sb.append(FUNCTION_ARGUMENT_SEPARATOR);
//	    sb.append(",");
	    	sb.append("'").append(functionArgument2);
	    
	   
	    sb.append("~~~");
	    sb.append(rawArgument);
	    	
	    sb.append("~~~");
	    sb.append(functionNameFromUI);	
	    	
	    sb.append("~~~");
	    sb.append(cardno);
	    
	    sb.append("~~~");  	  
	    sb.append(clientId);		    
	   	
	    sb.append("~~~");
	    sb.append(ruleId);
	    
	    sb.append("~~~");
	    sb.append(uid);
	    
	    sb.append("~~~");
	    sb.append(ruleSubject);
	    
	    sb.append("~~~");
	    sb.append(paramName);	    
	    
	    sb.append("'");
	    
	       
	    sb.append(" ");	    
	    sb.append(")");
	    
	    logger.info(sb.toString());
	    return sb.toString();
	}




	@Override
	public String toString() {
		return "FunctionArguments [ruleId=" + ruleId + ", operand=" + operand + ", subject=" + subject
				+ ", rawParamName=" + rawParamName + ", rawFunctionName=" + rawFunctionName
				+ ", implementationFunctionName=" + implementationFunctionName + ", cardNo=" + cardNo + ", uid=" + uid
				+ ", ruleSubject=" + ruleSubject + ", clientId=" + clientId + ", paramName=" + paramName + "]";
	}

	public static String encodeSpecialChar(String param){
		String[] elements = param.split("','",-1);
		if(elements.length == 2){
			String elementZero = elements[0];
			String elementOne = elements[1];
			if(elementZero.contains(",")){
				elementZero = elementZero.replace(",", "COMMA");
			}
			if(elementOne.contains(",")){
				elementOne = elementOne.replace(",", "COMMA");
			}
			return elementZero+"','"+elementOne;	
		}else{
			return param;
		}
	}
	
	public static String decodeSpecialChar(String param){
		String[] elements = param.split("','",-1);
		if(elements.length == 2){
			String elementZero = elements[0];
			String elementOne = elements[1];
			if(elementZero.contains("COMMA")){
				elementZero = elementZero.replace("COMMA", ",");
			}
			if(elementOne.contains("COMMA")){
				elementOne = elementOne.replace("COMMA", ",");
			}
			return elementZero+"','"+elementOne;
		}else{
			return param;
		}
	}
	
	
}

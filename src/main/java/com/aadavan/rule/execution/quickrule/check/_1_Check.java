package com.aadavan.rule.execution.quickrule.check;

import java.util.ArrayList;
import java.util.List;

public class _1_Check {

    public static void main(String[] args) {
        String expression="cardNo SUM_AMOUNT_IN_TIME_SUCCESS_PRIMARY_ID_MIN 'cardNo'|'2500000'|'5'  && cardNo COUNT_IN_TIME_SUCCESS_PRIMARY_ID_MIN 'cardNo'|'3'|'5'";
        quickRuleCheck(expression, "RULE::8198::3DS::1568292319810");
    }

    private static void quickRuleCheck(String expression, String ruleId) {
        String tempArray[] = expression.split(" ", -1); // spliting the expression based on space, hence its comulsory for the expression to have space in between
        List elements = new ArrayList<String>();

        for(int i =0 ; i< tempArray.length; i++){
            StringBuilder log = new StringBuilder();
            String element = tempArray[i];
            if(element.startsWith("'") && element.endsWith("'")){
                //logger.info("--------------"+element);
            }
            log.append("element:("+ruleId+")"+element).append("<==>");
            if(element.startsWith("'") && element.endsWith("'") == false){
                int nexlement = i+1;
                String nextPlus = "";
                try {
                    while(nextPlus.endsWith("'") == false){

                        String nextString = tempArray[nexlement];
                        nextPlus = tempArray[nexlement];
                        element = element +" "+ nextPlus;
                        nexlement = nexlement + 1;
                        log.append("---number:"+nexlement+" >"+tempArray[nexlement]).append("<==>");

                    }
                }catch(Exception e){
                    e.printStackTrace();
                    //System.out.println("Error ClientId: "+eguardRequest.getId()+"long:"+log.toString() +", RuleId: "+ruleId+", ExceptionMessage:  trace :"+AppUtility.getStackTrace(e));
                }
                i = nexlement-1;
            }

            try {
                element = element.trim();
                if(element.length() == 0){
                    continue;
                }
            }catch(Exception e){
                e.printStackTrace();
                //System.out.println("Error ClientId: "+eguardRequest.getId()+", RuleId: "+ruleId+", ExceptionMessage:  trace :"+AppUtility.getStackTrace(e));
            }


            elements.add(element);
        }
        System.out.println("elements = " + elements);
    }
}

package com.aadavan.check.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkipExecutionLogicCheck {
    public static void main(String[] args) {
        List<String> policyList = new ArrayList<>();
        String erTxnType = "3DS";
        final List<List<String>> policies = Arrays.asList(Arrays.asList("POS", "3DS", "ABC"), Arrays.asList("3DS", "POS"));
        for (List<String> txnTypes: policies) {
            for (String txnType : txnTypes) {
                System.out.println("checking for = " + txnType);
                if (txnType.equalsIgnoreCase(erTxnType)) {
                    policyList.add(txnType);
                    break;
                }
            }
        }
        System.out.println(policyList);
    }
}

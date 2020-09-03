package com.aadavan.check;

import java.util.*;

public class UniqueCheck {

    public static void main(String[] args) {
        List<String> columnsEligibleForSummary = Arrays.asList("txnSourceType");

        Set<String> fields = new HashSet<>();
        fields.add("ruleRating");
        fields.add("id");
        fields.add("created");
        fields.add("request.txnSourceType");
        fields.add("request.instanceId");
        fields.add("request.channelId");

        for (String entityName : columnsEligibleForSummary) {
            fields.add("request."+entityName);
        }
        System.out.println("fields = " + fields);
    }
}

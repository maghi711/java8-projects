package com.aadavan.check.core.intf;

/**
 * Question: To see if the implemented interface method has override annotate on it.
 * Answer: Yes it does as of Java 8
 */
public class CCardImpl implements CardIntf {

    private String defaultStr = "Unknown";

    @Override
    public String getName() {
        return defaultStr;
    }
}

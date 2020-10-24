package com.aadavan.check.basic;

import java.util.Arrays;

import static com.aadavan.rule.execution.quickrule.check.GeneralUtility.isBlankNull;

public class NullCheck {

    public static void main(String[] args) {
        Arrays.asList("", null, "null", "Aadavan");
        String arg = null;
        if (isBlankNull(arg)) {
            System.out.println("argument is null/blank/\"\"");
        }
        else {
            System.out.println("arg = " + arg);
        }
    }
}

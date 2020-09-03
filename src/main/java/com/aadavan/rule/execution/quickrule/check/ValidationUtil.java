package com.aadavan.rule.execution.quickrule.check;

import java.util.Arrays;
import java.util.List;

/**
 *  common validation framework for trident
 * @author Sarath
 */
public class ValidationUtil {

    final static String REG_EXP = "[0-9]+([.][0-9]{1,2})?";
    final static List<String> OPERATORS = Arrays.asList("<", "<=", ">", ">=", "=");

    public static boolean isNumeric(String numberString) {

        return numberString != null && numberString.matches(REG_EXP);
    }

    public static boolean isNumbericExpression(String expression) {

        if (expression == null) {
            return false;
        }

        String[] data = expression.replace("'", "").split("\\s+");
        if (data.length == 3) {

            if (isNumeric(data[0]) && isNumeric(data[2]) && OPERATORS.contains(data[1])) {
                return true;
            }
        }
        return false;
    }
}

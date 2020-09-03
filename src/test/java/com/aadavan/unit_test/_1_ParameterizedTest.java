package com.aadavan.unit_test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class _1_ParameterizedTest {

    _1_StringHelper helper = new _1_StringHelper();

    String expectedOutput;
    String input;

    public _1_ParameterizedTest(String input, String expectedOutput) {
        this.expectedOutput = expectedOutput;
        this.input = input;
    }

    @Parameters
    public static Collection<String[]> testCondition() {
        String expectedOutput[][] = {
                {"AAD", "CD"},
                {"ACD", "CD"}
        };
        return Arrays.asList(expectedOutput);
    }

    @Test
    public void testTruncateIn() {
        assertEquals(expectedOutput, helper.truncateAInFirst2Positions(input));
    }

}

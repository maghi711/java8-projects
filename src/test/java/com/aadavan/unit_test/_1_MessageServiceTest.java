package com.aadavan.unit_test;

/*
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
*/
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class _1_MessageServiceTest {

    //@DisplayName("Test _1_MessageService.get()")
    @Test
    public void testGet() {
        assertEquals("Hello Aadavan", _1_MessageService.get());
    }

    @Test(expected = NullPointerException.class)
    public void testNull() {
        String st = null;
        st.length();
    }

    @Test(timeout = 34)
    public void performanceCheck() {
        int a[] = {24, 34, 23};
        for (int i = 1; i < 1000000; i++) {
            a[0]=i;
            Arrays.sort(a);
        }
    }

}

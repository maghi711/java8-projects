package com.aadavan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayCheck {
    public static void main(String[] args) {
        String mcc = "777";
        String name = "name";
        String desc = "desc";
        List<String> values = new ArrayList<>();
        values.add(mcc);
        values.add(name);
        values.add(desc);
        String[] a = new String[3];
        String[] record = values.toArray(a);

        String[] b = new String[3];
        b[0] = mcc;
        b[1] = name;
        b[2] = desc;
        System.out.println("b = " + Arrays.toString(b));
    }
}

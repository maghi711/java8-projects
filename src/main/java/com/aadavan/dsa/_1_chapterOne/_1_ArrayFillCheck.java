package com.aadavan.dsa._1_chapterOne;

import java.util.Arrays;
import java.util.Date;

public class _1_ArrayFillCheck {
    public static void main(String[] args) {
        Object[] a = new Object[4];

        Arrays.fill(a, new Date());
        System.out.println("Arrays.toString(a) = " + Arrays.toString(a));

        Arrays.fill(a, 22);
        System.out.println("Arrays.toString(a) = " + Arrays.toString(a));

        Arrays.fill(a, "Aadavan");
        System.out.println("Arrays.toString(a) = " + Arrays.toString(a));
    }

}

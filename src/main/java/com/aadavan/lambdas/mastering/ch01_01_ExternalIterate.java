package com.aadavan.lambdas.mastering;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class ch01_01_ExternalIterate {
    public static void main(String[] args) {
        List<Point> points = Arrays.asList(new Point(1, 2), new Point(2, 3));
        for (Point p : points) {
            p.translate(1,1);
        }
    }
}

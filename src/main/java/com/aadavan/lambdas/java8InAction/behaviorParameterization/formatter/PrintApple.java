package com.aadavan.lambdas.java8InAction.behaviorParameterization.formatter;

import com.aadavan.lambdas.java8InAction.behaviorParameterization.Apple;

import java.util.List;

public class PrintApple {

    public static void main(String[] args) {
        List<Apple> apples = getApplesList();
        printApples(apples, new AppleFormatter() {
            @Override
            public String format(Apple apple) {
                return apple.toString();
            }
        });
        printApples(apples, new AppleFormatter() {
            @Override
            public String format(Apple apple) {
                return "Color of the apple is " + apple.getColor();
            }
        });
        printApples(apples, new AppleFormatter() {
            @Override
            public String format(Apple apple) {
                return "Weight of the apple is " + apple.getWeight();
            }
        });
    }

    static void printApples(List<Apple> apples, AppleFormatter formatter) {
        for (Apple apple: apples) {
            String output = formatter.format(apple);
            System.out.println(output);
        }
    }

    interface AppleFormatter {
        String format(Apple apple);
    }

    static List<Apple> getApplesList() {
        return List.of(
                Apple.newApple("green", 100, 10),
                Apple.newApple("green", 90, 10),
                Apple.newApple("green", 110, 10),
                Apple.newApple("red", 120, 12),
                Apple.newApple("red", 130, 12),
                Apple.newApple("red", 140, 12)
        );
    }
}

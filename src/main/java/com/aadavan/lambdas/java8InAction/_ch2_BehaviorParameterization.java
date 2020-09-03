package com.aadavan.lambdas.java8InAction;

import com.aadavan.lambdas.predicate.Predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _ch2_BehaviorParameterization {

    static List<Apple> appleList = new ArrayList<>();

    static {
        initAppleInventory();
    }

    public static void main(String[] args) {
        System.out.println("appleList = " + appleList);
        System.out.println("filterGreenApples() = " + filterAppleByColor("green"));
        System.out.println("filterGreenApples() = " + filterAppleByColor("red"));
        System.out.println("filterGreenApples() = " + filterAppleByColor("blue"));

        System.out.println(filterApple((a) -> "green".equalsIgnoreCase(a.getColor())));
        System.out.println(filterApple((a) ->
            a.getWeight() >= 125
        ));
        AppleFormatter formatter = (a) -> { return a.toString();};
        prettyPrintApples(formatter);
        prettyPrintApples((a) -> "Hey my weight is " + a.getWeight() + " gm and my color is " + a.getColor());
        prettyPrintApples((a) -> "I am " + a.getWeight() + " gm heavy, and my color is " + a.getColor());
        prettyPrintApples((a) -> {
            return a.getColor() + " is my color.";
        });
    }

    public static void prettyPrintApples(AppleFormatter formatter) {
        for (Apple a: appleList) {
            System.out.println(formatter.format(a));
        }
    }

    /**
     * paramter parameterization.
     * @param color
     * @return
     */
    public static List<Apple> filterAppleByColor(String color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: appleList) {
            if (color.equalsIgnoreCase(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * Behavior parameterization.
     * @param filterCondition
     * @return
     */
    public static List<Apple> filterApple(Predicate<Apple> filterCondition) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: appleList) {
            if (filterCondition.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * Initialize the apple inventory.
     */
    public static void initAppleInventory() {
        appleList.addAll(
                Arrays.asList(
                        new Apple("Red", 100),
                        new Apple("Green", 150),
                        new Apple("Blue", 125)
        ));
    }
}

interface AppleFormatter {
    String format(Apple a);
}

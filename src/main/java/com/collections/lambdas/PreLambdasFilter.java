package com.collections.lambdas;

import com.collections.lambdas.model.Apple;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreLambdasFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(PreLambdasFilter.class);

    private static final List<Apple> inventory = new ArrayList<>();

    static {
        BasicConfigurator.configure();
        initAppleInventory();
    }

    public static void main(String[] args) {
        LOGGER.info("The initialized apples are {}", inventory);
        List<Apple> greenApples = filterGreenApples(inventory);
        LOGGER.info("The green apples are {}", greenApples);
    }

    /**
     * First attempt at filtering apples.
     * @param inventory inventory of apples
     * @return list of apples
     */
    private static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory) {
            if ("green".equalsIgnoreCase(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    private static void initAppleInventory() {
        inventory.addAll(
            Arrays.asList(
                new Apple(1, "green", 10),
                new Apple(2, "red", 20),
                new Apple(3, "blue", 30),
                new Apple(4, "yellow", 40),
                new Apple(5, "cyan", 50),
                new Apple(6, "white", 60),
                new Apple(7, "black", 70),
                new Apple(8, "brown", 80),
                new Apple(9, "violet", 90),
                new Apple(10, "purple", 100)
            )
        );
    }
}

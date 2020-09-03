package com.aadavan.check.streams;

import java.util.*;
import java.util.stream.Collectors;

public class _1_Stream {
    public static void main(String[] args) {
        System.out.println("java7() = " + java7());
        System.out.println("java8() = " + java8());

        java8dish();
    }

    private static void java8dish() {
        List<Dish> allDishes = getAllDishes();
        System.out.println("allDishes = " + allDishes);
        List<String> lowCalorieDishes = allDishes.stream()
                .filter(d -> d.getCalorie() < 400)
                .sorted(Comparator.comparing(Dish::getName))
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println("lowCalorieDishes = " + lowCalorieDishes);
    }

    private static List<String> java8() {
        List<String> menu = getDishes();
        List<String> lowCalorieDishes = menu.stream()
                .filter(s -> s.contains("a"))
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        return lowCalorieDishes;
    }

    private static List<String> java7() {
        List<String> menu = getDishes();
        List<String> lowCalories = new ArrayList<>();

        // Filter elements using accumulator.
        for (String dish: menu) {
            if (dish.contains("a")) {
                lowCalories.add(dish);
            }
        }

        // Sort the dishes with anonymous classes.
        Collections.sort(lowCalories, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        // Process the sorted list to select the names of dishes.
        List<String> lowCalorieDishesNames = new ArrayList<>();
        for (String name: lowCalories) {
            lowCalorieDishesNames.add(name);
        }
        return lowCalorieDishesNames;
    }

    private static List<String> getDishes() {
        return Arrays.asList("Dosa", "Idli", "Vada", "Chutney", "Sambhar");
    }

    private static List<Dish> getAllDishes() {
        return Arrays.asList(
                new _1_Stream.Dish(300, "Dosa"),
                new _1_Stream.Dish(250, "Idli"),
                new _1_Stream.Dish(450, "Chutney"),
                new _1_Stream.Dish(600, "Vada"),
                new _1_Stream.Dish(500, "Sambhar")
        );
    }
    static class Dish {
        int calorie;
        String name;

        public Dish(int calorie, String name) {
            this.calorie = calorie;
            this.name = name;
        }

        public int getCalorie() {
            return calorie;
        }

        public void setCalorie(int calorie) {
            this.calorie = calorie;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Dish{" +
                    "calorie=" + calorie +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}

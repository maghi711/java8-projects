package com.aadavan.lambdas.function;

public class TestFunction {

    public static void main(String[] args) {
        Function<Meteor, Integer> readCelsius = m -> m.getTemperature();
        Function<Integer, Double> celsiusToFarhenheit = t -> t * 9d/5d + 32d;
        Integer temperature = readCelsius.apply(new Meteor(100));
        System.out.println("readCelsius = " + temperature);
        System.out.println("celsiusToFarhenheit = " + celsiusToFarhenheit.apply(temperature));
    }
}

package com.aadavan.exercises.ch02;

/**
 * Create a program that prompts for your name
 * and prints a greeting using your name.
 */
public class _1_GreetingApplication {

    public static void main(String[] args) {
        _1_Greeting greeting = new _1_Greeting();
        String name = greeting.getName("Aadavan");
        System.out.println("Hello " + name + ", nice to meet you!");
    }

}

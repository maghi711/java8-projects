package com.aadavan.check;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NumberList {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("781", "757", "758");
        Random random = new Random();
        int i = 1;
        while(i < 10) {
            int randomNumber = generateRandom(random);
            System.out.println("randomNumber = " + randomNumber);
            i++;
        }
    }

    public static int generateRandom(Random random) {
        return random.nextInt(3);
    }
}

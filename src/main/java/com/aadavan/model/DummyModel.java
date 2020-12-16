package com.aadavan.model;

import java.util.Random;

public class DummyModel implements ModelEngine {
    Random random = new Random();
    @Override
    public int getScore(String data) {
        if(data == null) {
            throw new NullPointerException("data passed in cannot be null");
        }
        System.out.println("Generating score based on the data passed in " + data);
        return random.nextInt(1000);
    }
}

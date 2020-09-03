package com.aadavan.unit_test.links;

import java.util.HashMap;
import java.util.Map;

public class Profile {
    private Map<String, Answer> answerMap = new HashMap<>();
    private int score;
    private String name;

    public Profile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

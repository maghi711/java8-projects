package com.aadavan.unit_test.junittest_1.scores;

import java.util.ArrayList;
import java.util.List;

public class ScoreCollection {
    private List<Scoreable> scores = new ArrayList<>();

    public void add(Scoreable score) {
        scores.add(score);
    }

    public int arithmeticMean() {
        int total = scores.stream().mapToInt(Scoreable::getScore).sum();
        return total / scores.size();
    }
}

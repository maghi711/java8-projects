package com.aadavan.unit_test.junittest_1.scores;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class _2_ScoreCollection {
    private List<Supplier<Integer>> scores = new ArrayList<>();

    public void add(Supplier<Integer> score) {
        scores.add(score);
    }

    public Integer getArithmeticMean() {
        final int sum = scores.stream().mapToInt(Supplier::get).sum();
        return sum/scores.size();
    }
}

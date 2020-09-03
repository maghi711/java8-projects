package com.aadavan.unit_test.junittest_2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreCollection {
    private List<Scoreable> scores = new ArrayList<>();

    public void add(Scoreable scoreable) {
        scores.add(scoreable);
    }

    public BigInteger arithmeticMean() {
        BigInteger actualResult = new BigInteger("0");
        final int size = scores.size();
        for (int i = 0; i < size; i++) {
            actualResult = actualResult.add(scores.get(i).getScore());
        }
        BigInteger result = actualResult.divide(new BigInteger(size+""));
        return result;
    }
}

package com.aadavan.dsa._1_chapterOne;

public final class PowerCalculator {
    public static final PowerCalculator INSTANCE = new PowerCalculator();
    private int base;
    private int exponent;
    private PowerCalculator() {
    }

    public long calculate(int base, int exponent) {
        assert exponent >= 0 : "exponent can't be < 0";
        long result = 1;
        for (int i = 0; i < exponent; ++i) {
            result = result * base;
        }
        return result;
    }
}

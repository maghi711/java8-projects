package com.aadavan.dsa._1_chapterOne;

import org.junit.Assert;
import org.junit.Test;

public class PowerCalculatorTest {

    @Test
    public void anythingRaisedToThePowerOfOneIsItself() {
        PowerCalculator calculator = PowerCalculator.INSTANCE;
        Assert.assertEquals(0, calculator.calculate(0,1));
        Assert.assertEquals(1, calculator.calculate(1,1));
        Assert.assertEquals(27, calculator.calculate(27,1));
        Assert.assertEquals(143, calculator.calculate(143,1));
    }

    @Test
    public void testArbitary() {
        PowerCalculator calculator = PowerCalculator.INSTANCE;
        Assert.assertEquals(0, calculator.calculate(0,2));
        Assert.assertEquals(1, calculator.calculate(1,2));
        Assert.assertEquals(4, calculator.calculate(2,2));
        Assert.assertEquals(8, calculator.calculate(2,3));
        Assert.assertEquals(27, calculator.calculate(3,3));

    }

}

package com.aadavan.unit_test.junittest_2;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.junit.*;

import java.math.BigInteger;

/**
 * 1) Arrange
 * 2) Act
 * 3) Assert
 */
public class ScoreCollectionTest {

    /**
     * Scenario 1) Sum of two numbers, 5 and 7 and its arithmetic mean is 6
     * @throws Exception
     */
    @Test
    public void answersArithmeticMeanOfTwoNumbers() throws Exception {
        // Arrange
        ScoreCollection collection = new ScoreCollection();
        collection.add(() -> new BigInteger("5"));
        collection.add(() -> new BigInteger("7"));

        // Act
        BigInteger actualResult = collection.arithmeticMean();

        // Assert
        assertThat("Must be equal", actualResult, equalTo(new BigInteger("6")));
    }

    @Test
    public void arithmeticMeanOfZeros() {
        //Arrange
        ScoreCollection scoreCollection = new ScoreCollection();
        scoreCollection.add(() -> new BigInteger("0"));
        scoreCollection.add(() -> new BigInteger("0"));

        // Act
        BigInteger actualResult = scoreCollection.arithmeticMean();

        // Assert
        assertThat(actualResult, equalTo(new BigInteger("0")));
    }

    @Test
    public void arithmeticMeanOfMaxNumbers() {
        // Arrange
        ScoreCollection scoreCollection = new ScoreCollection();
        final int maxValue = Integer.MAX_VALUE;
        scoreCollection.add(() -> new BigInteger(maxValue+""));
        scoreCollection.add(() -> new BigInteger(maxValue+""));

        // Act
        BigInteger actualResult = scoreCollection.arithmeticMean();

        // Assert
        assertThat(actualResult, equalTo(new BigInteger(maxValue+"")));
    }

}

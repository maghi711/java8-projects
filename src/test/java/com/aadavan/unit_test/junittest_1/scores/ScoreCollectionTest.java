package com.aadavan.unit_test.junittest_1.scores;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class ScoreCollectionTest {
    @Test
    public void arithmeticMeanOfTwoNumbers() {
        // Arrange
        ScoreCollection collection = new ScoreCollection();
        collection.add(() -> 5);
        collection.add(() -> 7);

        // Act
        int actualResult = collection.arithmeticMean();

        // Assert
        assertThat("Something wrong, fix it", actualResult, equalTo(6));
    }

    @Test
    public void arithmeticMeanWIthSupplier() {
        // Arrange
        _2_ScoreCollection collection = new _2_ScoreCollection();
        collection.add(() -> 10);
        collection.add(() -> 10);
        collection.add(() -> 10);

        // Act
        Integer actualResult = collection.getArithmeticMean();

        // Assert
        assertThat(actualResult, equalTo(10));
    }

}

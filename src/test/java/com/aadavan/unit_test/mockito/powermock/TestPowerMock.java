package com.aadavan.unit_test.mockito.powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.List;

import static org.powermock.api.mockito.PowerMockito.*;

/*
@PrepareForTest({UtilityClass.class})
PowerMockito.mockStatic(UtilityClass.class);
when(UtilityClass.staticMethod(anyLong())).thenReturn(150);

PowerMockito.verifyStatic();
UtilityClass.staticMethod(1 + 2 + 3);
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({UtilityClass.class})
public class TestPowerMock {

    //Algorithm for Static Method Mocking
    //1) Use Specific Runner - @RunWith(PowerMockRunner.class)
    //2) Initialize Utility/Static Method class for mocking
        // 1) @PrepareForTest({UtilityClass.class})
        // 2) PowerMockito.mockStatic(UtilityClass.class);
    //3) Mock it

    @Mock
    Dependency dependency; // The interface which is being mocked

    @InjectMocks
    StaticSystemUnderTest systemUnderTest; //

    @Test
    public void testStaticMethodMock() {
        //Given
        List<Integer> stats = Arrays.asList(1, 2, 3);

        //When
        when(dependency.retrieveAllStats()).thenReturn(stats); // Mock instance method
            // Static Method Mock Logic
        PowerMockito.mockStatic(UtilityClass.class);
        when(UtilityClass.staticMethod(Matchers.anyLong())).thenReturn(150);
        systemUnderTest.methodCallingAStaticMethod();

        //Then
        // To verify if the static method is called
        // Two Steps
        // 1) Verify Static
        // 2) Second one is the actual call
        PowerMockito.verifyStatic();
        UtilityClass.staticMethod(Matchers.anyInt());
    }
}

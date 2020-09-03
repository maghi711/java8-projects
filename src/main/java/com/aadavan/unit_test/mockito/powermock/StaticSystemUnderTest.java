package com.aadavan.unit_test.mockito.powermock;

import java.util.ArrayList;
import java.util.List;

public class StaticSystemUnderTest {

    private Dependency dependency;

    public int methodUsingArrayListConstructor() {
        ArrayList list = new ArrayList();
        return list.size();
    }

    /**
     * This needs two mocking. One is instance method and the other is a static method.
     * @return
     */
    public int methodCallingAStaticMethod() {
        List<Integer> stats = dependency.retrieveAllStats();
        long sum = 0;
        for (int stat: stats) {
            sum += stat;
        }
        return UtilityClass.staticMethod(sum);
    }

    private long privateMethodUnderTest() {
        List<Integer> stats = dependency.retrieveAllStats();
        long sum = 0;
        for (int stat: stats) {
            sum += stat;
        }
        return sum;
    }
}

package com.aadavan.lambdas.java8InAction.practise1;

import java.util.concurrent.TimeUnit;

public class ThreadUtilsForDelay {

    /**
     * Delay simulates the long running operation such as DB fetch, file read etc...
     * @param timeout
     */
    public static void delayInSeconds(long timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

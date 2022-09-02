package com.aadavan.completablefuture.async.practice2.timer;

import java.time.LocalDateTime;

public class IndianTimerService<String> implements TimerService<String> {

    @Override
    public String getTime() {
        //return LocalDateTime.now().toString();
        return null;
    }
}

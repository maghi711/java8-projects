package com.aadavan.completablefuture.async.practice2;

import java.time.LocalDateTime;

public interface TimerService<T> {
    T getTime();
}

class IndianTimerService<LocalDateTime> implements TimerService<LocalDateTime> {

    @Override
    public LocalDateTime getTime() {
        return null;
    }
}

class AsyncTask4 {
    public static void main(String[] args) {
        TimerService<LocalDateTime> time = new IndianTimerService<>();
        final LocalDateTime time1 = time.getTime();
        System.out.println("time1 = " + time1);
    }
}

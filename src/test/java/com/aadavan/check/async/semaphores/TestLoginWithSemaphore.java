package com.aadavan.check.async.semaphores;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class TestLoginWithSemaphore {

    @Test
    void givenLoginQueue_whenReachLimit_thenBlocked() {
        int slots = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(slots);
        final LoginWithSemaphore loginQueue = new LoginWithSemaphore(slots);
        IntStream.range(0, slots)
                .forEach(user -> {
                    executorService.execute(loginQueue::tryLogin);
                });
        executorService.shutdown();

        assertEquals(0, loginQueue.availableSlots());
        assertFalse(loginQueue.tryLogin());
    }

    @Test
    void logout() {
    }

    @Test
    void availableSlots() {
    }
}
package com.aadavan.check.async.semaphores;

import java.util.concurrent.Semaphore;

public class LoginWithSemaphore {
    private Semaphore semaphore;

    public LoginWithSemaphore(int slots) {
        this.semaphore = new Semaphore(slots);
    }

    boolean tryLogin() {
        return semaphore.tryAcquire();
    }

    void logout() {
        semaphore.release();
    }

    int availableSlots() {
        return semaphore.availablePermits();
    }
}

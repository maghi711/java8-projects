package com.aadavan.concurrency.boundedbuffer;

import java.util.LinkedList;
import java.util.Queue;

public class DataQueue {

    private final Queue<Message> queue = new LinkedList<>();
    private final int maxSize;
    private final Object QUEUE_FULL = new Object();
    private final Object QUEUE_EMPTY = new Object();

    private volatile int currentSize = 0;

    DataQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public boolean isFull() {
        synchronized (queue) {
            return currentSize == maxSize;
        }
    }

    public void waitOnFull() throws InterruptedException {
        synchronized (QUEUE_FULL) {
            QUEUE_FULL.wait();
        }
    }

    public void waitOnEmpty() throws InterruptedException {
        synchronized (QUEUE_EMPTY) {
            QUEUE_EMPTY.wait();
        }
    }

    public void add(Message message) {
        synchronized (queue) {
            currentSize++;
            queue.add(message);
        }
    }

    public Message remove() {
        synchronized (queue) {
            currentSize--;
            return queue.poll();
        }
    }

    public void notifyAllForEmpty() {
        synchronized (QUEUE_EMPTY) {
            QUEUE_EMPTY.notifyAll();
        }
    }

    public void notifyAllForFull() {
        synchronized (QUEUE_FULL) {
            QUEUE_FULL.notifyAll();
        }
    }

    public boolean isEmpty() {
        synchronized (queue) {
            return queue.isEmpty();
        }
    }
}

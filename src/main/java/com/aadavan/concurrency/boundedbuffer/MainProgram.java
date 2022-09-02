package com.aadavan.concurrency.boundedbuffer;

public class MainProgram {
    private static final int MAX_QUEUE_CAPACITY = 500;

    public static void main(String[] args) throws Exception {
        DataQueue dataQueue = new DataQueue(MAX_QUEUE_CAPACITY);

        Producer producer = new Producer(dataQueue);
        Thread pThread = new Thread(producer);

        Consumer consumer = new Consumer(dataQueue);
        Thread cThread = new Thread(consumer);

        pThread.start();
        cThread.start();

        Thread.sleep(1000);
        producer.stop();
        consumer.stop();

    }
}

package com.aadavan.concurrency.boundedbuffer;

public class Consumer implements Runnable {

    private final DataQueue dataQueue;
    private volatile boolean runFlag = true;

    public Consumer(DataQueue dataQueue) {
        this.dataQueue = dataQueue;
    }

    @Override
    public void run() {
        consume();
    }

    private void consume() {
        System.out.println("Running consume");
        while (runFlag) {
            Message message;
            if (dataQueue.isEmpty()) {
                try {
                    System.out.println("Waiting on empty");
                    dataQueue.waitOnEmpty();
                } catch (InterruptedException e) {
                    break;
                }
            }
            if (!runFlag) {
                break;
            }
            message = dataQueue.remove();
            dataQueue.notifyAllForFull();
            useMessage(message);
        }
    }

    private void useMessage(Message message) {
        System.out.println("Got this message = " + message);
    }

    public void stop() {
        runFlag = false;
        dataQueue.notifyAllForEmpty();
    }
}

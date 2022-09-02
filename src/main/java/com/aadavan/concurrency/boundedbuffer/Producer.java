package com.aadavan.concurrency.boundedbuffer;

public class Producer implements Runnable {

    private final DataQueue dataQueue;
    private volatile boolean runFlag = true;

    public Producer(DataQueue dataQueue) {
        this.dataQueue = dataQueue;
    }

    @Override
    public void run() {
        produce();
    }

    private void produce() {
        System.out.println("Running produce");
        while (runFlag) {
            Message message = new Message(1, "Aadavan is coming");
            while (dataQueue.isFull()) {
                try {
                    dataQueue.waitOnFull();
                } catch (InterruptedException e) {
                    break;
                }
            }
            if (!runFlag) {
                break;
            }
            dataQueue.add(message);
            dataQueue.notifyAllForEmpty();
        }
    }

    public void stop() {
        runFlag = false;
        dataQueue.notifyAllForFull();
    }
}

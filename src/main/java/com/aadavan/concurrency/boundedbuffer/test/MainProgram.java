package com.aadavan.concurrency.boundedbuffer.test;

import java.util.LinkedList;
import java.util.Queue;

public class MainProgram {

    public static void main(String[] args) {
        DataQueue queue = new DataQueue();
        Thread pThread = new Thread(new Producer(queue));
        Thread cThread = new Thread(new Consumer(queue));

        pThread.start();
        cThread.start();
    }

    static class Producer implements Runnable {
        private final DataQueue dataQueue;

        public Producer(DataQueue dataQueue) {
            this.dataQueue = dataQueue;
        }

        public DataQueue getDataQueue() {
            return dataQueue;
        }

        @Override
        public void run() {
            System.out.println("Producer");

        }
    }

    static class Consumer implements Runnable {
        private final DataQueue dataQueue;

        public Consumer(DataQueue dataQueue) {
            this.dataQueue = dataQueue;
        }

        public DataQueue getDataQueue() {
            return dataQueue;
        }

        @Override
        public void run() {
            System.out.println("Consumer");
        }
    }

    static class DataQueue {
        private final Queue<Message> queue = new LinkedList();

        public Queue<Message> getQueue() {
            return queue;
        }
    }

    static class Message {
        private final int id;
        private final String name;

        public Message(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }
    }
}

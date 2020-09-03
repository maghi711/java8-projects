package com.aadavan.threads.nonblocking.iteration1._1;

import java.util.concurrent.TimeUnit;

public class JoinCheck {

    static class TestJoinMethod4 extends Thread{
        public void run(){
            JoinCheck.sleep(2);
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String args[]) throws InterruptedException {
        TestJoinMethod4 t1= new TestJoinMethod4();
        TestJoinMethod4 t2= new TestJoinMethod4();

        t1.start();
        t2.start();

        sleep(4);
        t1.join();
        t2.join();
        System.out.println("main thread exiting ");
    }

    public static void sleep (long seconds) {
        System.out.println("waiting seconds = " + seconds);
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



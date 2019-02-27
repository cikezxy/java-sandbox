package com.cikezxy.sandbox.concurrency;

import java.util.Date;

public class JoinExample {

    public static void main(String[] args) throws InterruptedException {
        // case1: 没有超时的join，主线程在t2结束后被唤醒
        // case1();

        // case2: 带超时的join，超时后被唤醒
        // case2();
        // case3: 若线程未开始或已结束，join直接返回
        case3();
    }

    private static void case1() throws InterruptedException {
        Thread t2 = new SampleThread(5);
        t2.start();
        System.out.println(new Date() + " invoke join");
        t2.join();
        System.out.println(new Date() + " return from join");
    }

    private static void case2() throws InterruptedException {
        Thread t2 = new SampleThread(5);
        t2.start();
        System.out.println(new Date() + " invoke join");
        t2.join(1000);
        System.out.println(new Date() + " return from join");
    }

    private static void case3() throws InterruptedException {
        Thread t2 = new SampleThread(5);
        System.out.println(new Date() + " invoke join");
        t2.join();
        System.out.println(new Date() + " return from join");
    }
}

class SampleThread extends Thread {
    public int processingCount;

    SampleThread(int processingCount) {
        this.processingCount = processingCount;
        System.out.println("Thread Created");
    }

    @Override
    public void run() {
        System.out.println("Thread " + this.getName() + " started");
        while (processingCount > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread " + this.getName() + " interrupted");
            }
            processingCount--;
        }
        System.out.println("Thread " + this.getName() + " exiting");
    }
}

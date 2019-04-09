package com.cikezxy.sandbox.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserExample {

    private static final int THREAD_NUM = 5;

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUM);
        Phaser phaser = new Phaser();

        Runnable[] tasks = new Runnable[THREAD_NUM];
        for (int i = 0; i < THREAD_NUM; i++) {
            tasks[i] = new PhaserTask(phaser);
        }
        for (int i = 0; i < THREAD_NUM; i++) {
            executorService.execute(tasks[i]);
        }
        executorService.shutdown();
    }
}

class PhaserTask implements Runnable {
    private Phaser phaser;

    public PhaserTask(Phaser phaser) {
        this.phaser = phaser;
        this.phaser.register();
    }

    @Override
    public void run() {
        try {
            String name = Thread.currentThread().getName();

            System.out.println(name + " start, current phase:" + phaser.getPhase());
            TimeUnit.SECONDS.sleep((long) (Math.random() * 10));

            System.out.println(name + " wait others");
            phaser.arriveAndAwaitAdvance();
            System.out.println(name + " wake up, current phase:" + phaser.getPhase());

            phaser.arriveAndDeregister();
            System.out.println(name + " finish and de-register");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

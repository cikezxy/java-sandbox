package com.cikezxy.sandbox.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserExample {

    private static final int THREAD_NUM = 5;

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUM);
        Phaser phaser = new Phaser(THREAD_NUM);

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
            int i = 1;

            System.out.println(name + " start, round " + i);
            TimeUnit.SECONDS.sleep((long) (Math.random() * 10));
            phaser.arriveAndDeregister();
            System.out.println(name + " end and wait, round " + i);
            phaser.arriveAndAwaitAdvance();
            System.out.println(name + " advance, round " + i);
            i++;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

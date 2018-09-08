package com.cikezxy.sandbox.concurrency;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample {

    private ExecutorService pool = Executors.newFixedThreadPool(5);

    public void execute(int n) {

        CountDownLatch countDownLatch = new CountDownLatch(n);

        for (int i = 0; i < n; i++) {
            final int id = i;
            pool.execute(new Runnable() {

                @Override
                public void run() {
                    System.out.println(String.format("task %d start...", id));
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(String.format("task %d finish...", id));
                    countDownLatch.countDown();
                }
            });
        }

        try {
            // countDownLatch.await(3,TimeUnit.SECONDS);
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread finished");
        pool.shutdown();
    }

    public static void main(String[] args) {
        CountDownLatchExample countDownLatchExample = new CountDownLatchExample();
        countDownLatchExample.execute(3);
    }
    // output:
    // task 1 start...
    // task 0 start...
    // task 2 start...
    // task 0 finish...
    // task 1 finish...
    // task 2 finish...
    // main thread finished

}

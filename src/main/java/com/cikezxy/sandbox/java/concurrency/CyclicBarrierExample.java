package com.cikezxy.sandbox.java.concurrency;

import java.util.Random;
import java.util.concurrent.*;

public class CyclicBarrierExample {

    public static void main(String[] args) {
        CyclicBarrierExample cyclicBarrierExample = new CyclicBarrierExample();
        cyclicBarrierExample.start(5);
    }

    public void start(int n) {
        ExecutorService executorService = Executors.newFixedThreadPool(n);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(n);
        System.out.println("GAME START");
        for (int i = 0; i < n; i++) {
            executorService.execute(new Runner(cyclicBarrier, i));
        }
        try {
            executorService.shutdown();
            executorService.awaitTermination(30, TimeUnit.SECONDS);
            System.out.println("GAME OVER");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class Runner implements Runnable {

        private static Random rand = new Random(47);

        private final static int N = 3;

        private CyclicBarrier barrier;
        private int id;

        public Runner(CyclicBarrier barrier, int id) {
            this.barrier = barrier;
            this.id = id;
        }

        @Override
        public void run() {
            for (int i = 0; i < N; i++) {
                try {
                    System.out.println(String.format("Round %d, Runner %d start", i, id));
                    TimeUnit.SECONDS.sleep(rand.nextInt(3));
                    System.out.println(String.format("Round %d, Runner %d finished", i, id));
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

// GAME START
// Round 0, Runner 0 start
// Round 0, Runner 1 start
// Round 0, Runner 2 start
// Round 0, Runner 2 finished
// Round 0, Runner 0 finished
// Round 0, Runner 1 finished
// Round 1, Runner 1 start
// Round 1, Runner 2 start
// Round 1, Runner 0 start
// Round 1, Runner 2 finished
// Round 1, Runner 1 finished
// Round 1, Runner 0 finished
// Round 2, Runner 0 start
// Round 2, Runner 2 start
// Round 2, Runner 1 start
// Round 2, Runner 1 finished
// Round 2, Runner 0 finished
// Round 2, Runner 2 finished
// GAME OVER

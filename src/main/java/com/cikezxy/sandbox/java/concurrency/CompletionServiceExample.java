package com.cikezxy.sandbox.java.concurrency;

import java.util.Random;
import java.util.concurrent.*;

public class CompletionServiceExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Random random = new Random(47);
        ExecutorService pool = Executors.newFixedThreadPool(3);
        CompletionService<String> completionService = new ExecutorCompletionService<>(pool);

        for (int i = 1; i <= 3; i++) {
            completionService.submit(new Task(i, random.nextInt(7)));
        }

        for (int i = 0; i < 3; i++) {
            Future<String> result = completionService.take();
            System.out.println(result.get());
        }

        pool.shutdown();
    }

    private static class Task implements Callable {
        private int executionSeconds = 0;
        private int id;

        public Task(int id, int executionSeconds) {
            this.id = id;
            this.executionSeconds = executionSeconds;
        }

        @Override
        public String call() throws Exception {
            System.out.println("task " + id + " start");
            TimeUnit.SECONDS.sleep(executionSeconds);
            System.out.println("task " + id + " finished, execution time = " + executionSeconds);
            return "Task " + id + " result returned";
        }
    }
}

// output:
// task 1 start
// task 2 start
// task 3 start
// task 2 finished, execution time = 2
// Task 2 result returned
// task 1 finished, execution time = 6
// Task 1 result returned
// task 3 finished, execution time = 6
// Task 3 result returned

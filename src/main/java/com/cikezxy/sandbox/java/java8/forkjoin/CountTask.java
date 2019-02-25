package com.cikezxy.sandbox.java.java8.forkjoin;

import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 10_000_000;
    private int start;
    private int end;

    @Override
    protected Integer compute() {
        return null;
    }
}

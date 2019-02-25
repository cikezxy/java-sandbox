package com.cikezxy.sandbox.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WaitNotifyExample {

    public static void main(String[] args) throws InterruptedException {
        Car car = new Car();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new WaxOnTask(car));
        executorService.execute(new WaxOffTask(car));
        TimeUnit.SECONDS.sleep(5);
        executorService.shutdownNow();
    }
}

class Car {

    private boolean waxOn = false;

    public synchronized void waxed() {
        waxOn = true;
        this.notifyAll();
    }

    public synchronized void buffed() {
        waxOn = false;
        this.notifyAll();
    }

    public synchronized void waitForWax() throws InterruptedException {
        while (!waxOn) {
            this.wait();
        }
    }

    public synchronized void waitForBuffing() throws InterruptedException {
        while (waxOn) {
            this.wait();
        }
    }
}

class WaxOnTask implements Runnable {

    private Car car;

    public WaxOnTask(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForBuffing();
                System.out.println("wax on");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
            }
        } catch (Exception e) {
            System.out.println("exit via interrupt");
        }
        System.out.println("ending wax on task");
    }
}

class WaxOffTask implements Runnable {

    private Car car;

    public WaxOffTask(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForWax();
                System.out.println("wax off");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (Exception e) {
            System.out.println("exit via interrupt");
        }
        System.out.println("ending wax off task");
    }
}

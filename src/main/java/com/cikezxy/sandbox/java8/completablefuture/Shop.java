package com.cikezxy.sandbox.java8.completablefuture;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Shop {

    private String name;
    private Random random = new Random(47);

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Double getPrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    private void delay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

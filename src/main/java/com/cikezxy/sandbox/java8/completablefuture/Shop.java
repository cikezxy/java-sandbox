package com.cikezxy.sandbox.java8.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

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
        return calculatePrice(product);
    }

    private Double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> future = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                future.complete(price);
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        }).start();
        return future;
    }

    private void delay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Stream.of(new Shop("a"), new Shop("b"), new Shop("c"), new Shop("d"));
    }
}

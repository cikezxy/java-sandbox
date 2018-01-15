package com.cikezxy.sandbox.java8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ApplesFilter {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        List<Apple> greenApples = filterApples(inventory, (Apple a) -> "green".equals(a.getColor()));
        System.out.println("绿苹果："+ greenApples);

        inventory.sort((Apple a1, Apple a2)-> a1.getWeight().compareTo(a2.getWeight()));
        inventory.sort(Comparator.comparing(Apple::getWeight));
        System.out.println("按重量排序："+inventory);

    }

    public static List<Apple> filterApples(List<Apple> apples, Predicate<Apple> p) {

        List<Apple> result = new ArrayList<>();
        for (Apple a : apples) {
            if (p.test(a)) {
                result.add(a);
            }
        }
        return result;
    }


    public static class Apple {

        private String color = "";
        private int weight = 0;

        public Apple() {
        }

        public Apple(int weight, String color) {
            this.color = color;
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }
}

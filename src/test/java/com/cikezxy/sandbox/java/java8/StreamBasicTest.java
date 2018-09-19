package com.cikezxy.sandbox.java.java8;

import java.io.File;
import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;

import com.cikezxy.sandbox.java8.stream.Dish;

public class StreamBasicTest {

    List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    //函数的复合
    @Test
    public void testFunctionAndThenCompose() {
        Function<Integer, Integer> f = (x) -> x + 1; //f(x) = x+1
        Function<Integer, Integer> g = (x) -> 2 * x; //g(x) = 2* x
        Function<Integer, Integer> h = f.andThen(g); //h(x) = g(f(x))
        Function<Integer, Integer> i = f.compose(g); //i(x) = f(g(x))

        Assert.assertTrue(h.apply(2) == 6);
        Assert.assertTrue(i.apply(2) == 5);
    }

    // 比较器链：先按热量倒序排列，再按名字正序排列
    @Test
    public void testComparatorChain() {
        menu.sort(Comparator.comparing(Dish::getCalories)
                .reversed()
                .thenComparing(Dish::getName));
        System.out.println(menu);
    }

    // 谓词（Predicate）复合
    @Test
    public void testPredicateChain() {
        Predicate<Dish> fish = dish -> dish.getType().equals(Dish.Type.FISH);
        Predicate<Dish> lowCalories = dish -> dish.getCalories() < 400;

        //非鱼类且低能量：[rice, season fruit]
        System.out.println(menu.stream().filter(fish.negate().and(lowCalories)).collect(Collectors.toList()));

        //鱼类或低能量：[rice, season fruit, prawns, salmon]
        System.out.println(menu.stream().filter(fish.or(lowCalories)).collect(Collectors.toList()));
    }

    @Test
    public void testOperation() {
        //filter:谓词过滤 [french fries, rice, season fruit, pizza]
        System.out.println(menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList()));
        //distinct：去重 [1, 2, 3, 4, 5]
        System.out.println(Arrays.asList(1, 2, 2, 3, 4, 5).stream().distinct().collect(Collectors.toList()));
        //limit：截断 [french fries, rice]
        System.out.println(menu.stream().filter(Dish::isVegetarian).limit(2).collect(Collectors.toList()));
        //skip：跳过 [rice, season fruit, pizza]
        System.out.println(menu.stream().filter(Dish::isVegetarian).skip(1).collect(Collectors.toList()));

        //map：映射为新的集合
        //[pork:800, beef:700, chicken:400, french fries:530, rice:350, season fruit:120, pizza:550, prawns:300, salmon:450]
        System.out.println(menu.stream().map(dish -> dish.getName() + ":" + dish.getCalories()).collect(Collectors.toList()));

        //flatMap:用于输入本身就是一个流的情况 [h, e, l, o, w, r, d]
        System.out.println(Arrays.asList("hello", "word").stream().map(s -> s.split("")).flatMap(Arrays::stream)
                .distinct().collect(Collectors.toList()));

        //allMatch：所有素食能量都小于300时为真 false
        System.out.println(menu.stream().filter(Dish::isVegetarian).allMatch(dish -> dish.getCalories() < 300));

        //anyMatch：存在能量小于300的素食时为真 true
        System.out.println(menu.stream().filter(Dish::isVegetarian).anyMatch(dish -> dish.getCalories() < 300));

        //noneMatch：所有素食能量都小于300时为真 false
        System.out.println(menu.stream().filter(Dish::isVegetarian).noneMatch(dish -> dish.getCalories() < 300));

        //findAny: 找到任意素食 french fries
        menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(System.out::println);

        //findAny: 找到第一个素食 french fries
        menu.stream().filter(Dish::isVegetarian).findFirst().ifPresent(System.out::println);

        //forEach: 打印每个元素，无返回值
        menu.stream().forEach(System.out::println);

        //reduce: 传入初始值和操作---求所有食物的热量之和 4200
        System.out.println(menu.stream().map(Dish::getCalories).reduce(0, (a, b) -> a + b));

        //reduce: 不传入初值，返回Optional对象---求热量最大的元素 800
        menu.stream().map(Dish::getCalories).reduce(Integer::max).ifPresent(System.out::println);
    }

    @Test
    public void testIntStream() {
        System.out.println(IntStream.rangeClosed(1, 100).filter(value -> value % 2 == 0).count());
        IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> Arrays.asList(a, b, Math.sqrt(a * a + b * b)))
                        .filter(t -> t.get(2).doubleValue() % 1 == 0))
                .forEach(System.out::println);
    }

    @Test
    public void testCollectors(){
        //最大最小值
        System.out.println(menu.stream().collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))).orElse(null));
        System.out.println(menu.stream().collect(Collectors.minBy(Comparator.comparingInt(Dish::getCalories))).orElse(null));

        //计数
        System.out.println(menu.stream().collect(Collectors.counting()));
        System.out.println(menu.stream().count());

        // 求和 4200
        System.out.println(menu.stream().collect(Collectors.summingInt(Dish::getCalories)));

        //统计 IntSummaryStatistics{count=9, sum=4200, min=120, average=466.666667, max=800}
        System.out.println(menu.stream().collect(Collectors.summarizingInt(Dish::getCalories)));

        //连接字符串 pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon
        System.out.println(menu.stream().map(Dish::getName).collect(Collectors.joining(", ")));

        // reducing: reducing是一般化的收集器
        System.out.println(menu.stream().collect(Collectors.reducing(0,Dish::getCalories,(a,b)->a+b)));

        // groupingBy: 分组
        System.out.println(menu.stream().collect(Collectors.groupingBy(Dish::getType)));
        // groupingBy: 分组计数
        System.out.println(menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting())));
       // groupingBy: 每组中热量最高的 collectingAndThen：收集器转换
        System.out.println(menu.stream().collect(Collectors.groupingBy(Dish::getType,
                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),Optional::get))));

    }

}

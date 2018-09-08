package com.cikezxy.sandbox.java.java8;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

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
            new Dish("salmon", false, 450, Dish.Type.FISH) );
    @Test
    public void testGetLowCaloricDishesName() {
        List<String> names = menu.stream().filter(dish -> dish.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories)).map(Dish::getName).collect(Collectors.toList());

        System.out.println("testGetLowCaloricDishesName:" + names);
    }

    @Test
    public void testDistinct() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        System.out.println("testDistinct:");
        numbers.stream().distinct().forEach(System.out::println);
    }

    @Test
    public void testLimit() {
        new File("/").listFiles(File::isHidden);
        System.out.println("testLimit:");
        menu.stream().filter(dish -> dish.getCalories() > 300).limit(3).forEach(System.out::println);
    }

    @Test
    public void testSkip() {
        System.out.println("testSkip:");
        menu.stream().filter(dish -> dish.getCalories() > 300).skip(2).forEach(System.out::println);
    }

    // output:[h, e, l, o, w, r, d]
    @Test
    public void testFlatMap() {
        List list = Arrays.asList("hello", "world").stream().map(s -> s.split("")) // 得到Stream<String[]>
                .flatMap(Arrays::stream) // Arrays.stream得到Stream<Stream<String>>,flatMap得到Stream<String>
                .distinct().collect(Collectors.toList());
        System.out.println(list);
    }

    // output:[1, 3][1, 4][2, 3][2, 4][3, 3][3, 4]
    @Test
    public void testFlatMap2() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        List<int[]> results = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[] { i, j }))
                .collect(Collectors.toList());
        results.stream().map(array -> Arrays.toString(array)).forEach(System.out::println);
    }

    // output: true
    @Test
    public void testAnyMatch() {
        System.out.println(menu.stream().anyMatch(Dish::isVegetarian));
    }

    // output:false
    @Test
    public void testAllMatch() {
        System.out.println(menu.stream().allMatch(Dish::isVegetarian));
    }

    // output:false
    @Test
    public void testNoneMatch() {
        System.out.println(menu.stream().noneMatch(Dish::isVegetarian));
    }

    @Test
    public void testFind() {
        // findAny output:french fries 并行效率更高
        System.out.println(menu.stream().filter(Dish::isVegetarian).findAny().get());

        // findFirst output:french fries
        System.out.println(menu.stream().filter(Dish::isVegetarian).findFirst().get());
    }

    @Test
    public void testReduce() {
        List<Integer> numbers = Arrays.asList(4, 5, 3, 9);
        System.out.println(numbers.stream().reduce(1, (a, b) -> a * b));
        System.out.println(numbers.stream().reduce(0, (a, b) -> a + b));
    }

    @Test
    public void testMaxMin() {
        List<Integer> numbers = Arrays.asList(4, 5, 3, 9);
        System.out.println(numbers.stream().reduce(Integer::max).get());
        System.out.println(numbers.stream().reduce(Integer::min).get());
    }

    @Test
    public void testJoinUrl() {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("c", "1");
        map.put("b", "2");
        map.put("a", "3");
        String ss = map.entrySet().stream().map(s -> s.getKey() + "=" + s.getValue()).collect(Collectors.joining("&"));
        System.out.println(ss);
    }

}

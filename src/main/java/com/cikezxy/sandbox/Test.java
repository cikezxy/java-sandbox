package com.cikezxy.sandbox;

import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        String[] str = new String[] { "you", "wu" };
        List list = Arrays.asList(str);
        list.add("aaa");
        System.out.println(list);
        System.out.println("aaa");
    }
}

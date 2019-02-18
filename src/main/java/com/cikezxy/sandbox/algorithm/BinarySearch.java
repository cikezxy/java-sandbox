package com.cikezxy.sandbox.algorithm;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;

public class BinarySearch {

    public static int search(Comparable[] a, Comparable value) {
        int low = 0;
        int high = a.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (value.compareTo(a[mid]) == 0) {
                return mid;
            } else if (value.compareTo(a[mid]) > 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] { 1, 3, 5, 7, 9, 11, 13, 15 };
        System.out.println(StringUtils.leftPad("9999", 3, '0'));
        AtomicInteger counter = new AtomicInteger(0);

        for (int i = 0; i < 100; i++) {
            System.out
                    .println(StringUtils.leftPad(String.valueOf(Math.abs(counter.getAndAdd(10000000) % 1000)), 3, '0'));
        }
    }
}

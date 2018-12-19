package com.cikezxy.sandbox.algorithm;

public class QuickSort {

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int start, int end) {
        if (start >= end) return;

        //找到枢纽点后，枢纽点已经在正确的位置上，分组时不再考虑枢纽点元素
        int point = partition(a, start, end);
        sort(a, start, point-1);
        sort(a, point+1, end);
    }

    private static int partition(Comparable[] a, int start, int end) {
        Comparable pivot = a[end];
        int j = start;
        for (int i = start; i < end; i++) {
            if (a[i].compareTo(pivot) < 0) {
                swap(a, i, j);
                j++;
            }
        }
        swap(a, j, end);
        return j;
    }

    private static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{2, 1, 3, 9, 8, 7, 6, 5, 8};
        sort(a);
        for (int e : a) {
            System.out.print(e + ",");
        }
    }
}

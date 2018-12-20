package com.cikezxy.sandbox.algorithm;

public class CountingSort {

    public static void sort(int[] a) {

        // 查找最大值
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            max = max < a[i] ? a[i] : max;
        }

        // 初始化counting数组
        int[] countingArr = new int[max + 1];
        for (int i = 0; i < countingArr.length; i++) {
            countingArr[i] = 0;
        }

        // 计数并累加
        for (int i = 0; i < a.length; i++) {
            countingArr[a[i]]++;
        }
        for (int i = 1; i < countingArr.length; i++) {
            countingArr[i] = countingArr[i - 1] + countingArr[i];
        }

        int[] temp = new int[a.length];
        for (int i = a.length - 1; i >= 0; i--) {
            temp[countingArr[a[i]] - 1] = a[i];
            countingArr[a[i]]--;
        }

        for (int i = 0; i < a.length; i++) {
            a[i] = temp[i];
        }

    }

    public static void main(String[] args) {
        int[] ints = { 2, 5, 3, 0, 2, 3, 0, 3 };
        sort(ints);
        for (int e : ints) {
            System.out.print(e + ",");
        }
    }
}

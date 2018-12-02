package com.cikezxy.sandbox.algorithm;

public class SimpleSort {

    //选择排序
    public static void selectionSort(Comparable[] a) {
        if (a == null || a.length == 0) {
            return;
        }
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j].compareTo(a[min]) < 0) {
                    min = j;
                }
            }
            Comparable temp = a[i];
            a[i] = a[min];
            a[min] = temp;
        }
    }

    //冒泡排序
    public static void bubbleSort(Comparable[] a) {
        if (a == null || a.length == 0) {
            return;
        }
        for (int i = 0; i < a.length; i++) {
            boolean flag = false;
            for (int j = 0; j < a.length - i-1; j++) {
                if (a[j].compareTo(a[j + 1]) > 0) {
                    Comparable temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    flag = true;
                }
            }
            //没有交换发生，提前结束排序
            if(!flag){
                break;
            }
        }
    }

    //插入排序
    public static void insertSort(Comparable[] a) {
        if (a == null || a.length == 0) {
            return;
        }
        for (int i =1;i<a.length;i++){
            Comparable value = a[i];
            int j= i-1;
            for(;j>=0;j--){
                if(a[j].compareTo(value)>0){
                    a[j+1]=a[j];
                }else {
                    break;
                }
            }
            a[j+1]=value;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1, 3, 4, 2, 9, 2, 3, 4, 0};
        insertSort(arr);
        for (Integer e : arr) {
            System.out.print(e + ",");
        }
    }
}

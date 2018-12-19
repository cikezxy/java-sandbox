package com.cikezxy.sandbox.algorithm;

public class MergeSort {

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        //临时数组
        Comparable[] temp = new Comparable[hi - lo + 1];
        int i = lo, j = mid + 1, k = 0;
        while (i <= mid && j <= hi) {
            if (a[i].compareTo(a[j]) > 0) {
                temp[k++] = a[j++];
            } else {
                temp[k++] = a[i++];
            }
        }

        //把剩余的元素复制到临时数组
        int start = i, end = mid;
        if (j <= hi) {
            start = j;
            end = hi;
        }
        while (start <= end) {
            temp[k++] = a[start++];
        }

        //把临时数组的元素复制回原数组
        k = 0;
        while (k < temp.length) {
            a[lo + k] = temp[k];
            k++;
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{2,1,3,9,8,7,6,5,8};
        sort(a);
        for (int e:a){
            System.out.print(e+",");
        }
    }
}

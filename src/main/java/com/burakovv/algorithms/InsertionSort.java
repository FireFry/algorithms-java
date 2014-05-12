package com.burakovv.algorithms;

public class InsertionSort {

    public static void sort(int a[]) {
        sort(a, 0, a.length);
    }

    public static void sort(int a[], int offset, int size) {
        for (int i = offset + 1, limit = offset + size; i < limit; i++) {
            int k = a[i];
            int j = i - 1;
            while (j >= offset && a[j] > k) {
                a[j + 1] = k;
                j--;
            }
            a[j + 1] = k;
        }
    }

}

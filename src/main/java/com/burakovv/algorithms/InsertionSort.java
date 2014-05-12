package com.burakovv.algorithms;

import com.burakovv.data.ComparableData;

public class InsertionSort {

    public static void sort(ComparableData data) {
        int offset = data.getOffset();
        int size = data.getSize();
        for (int i = offset + 1, limit = offset + size; i < limit; i++) {
            int j = i - 1;
            while (j >= offset && data.compare(j, j + 1) > 0) {
                data.swap(j, j + 1);
                j--;
            }
        }

    }
}

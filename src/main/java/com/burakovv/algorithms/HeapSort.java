package com.burakovv.algorithms;

import com.burakovv.data.ComparableData;

public class HeapSort {

    public static void sort(ComparableData data) {
        int size = data.getSize();
        for (int i = size / 2; i >= 0; i--) {
            heapify(data, i, size);
        }
        for (int i = size - 1; i > 0; i--) {
            data.swap(0, i);
            heapify(data, 0, i);
        }
    }

    private static void heapify(ComparableData data, int i, int size) {
        int dataOffset = data.getOffset();
        int left = 2 * i;
        int right = left + 1;
        int max = i;
        if (left < size && data.compare(dataOffset + max, dataOffset + left) < 0) {
            max = left;
        }
        if (right < size && data.compare(dataOffset + max, dataOffset + right) < 0) {
            max = right;
        }
        if (max != i) {
            data.swap(dataOffset + i, dataOffset + max);
            heapify(data, max, size);
        }
    }

}

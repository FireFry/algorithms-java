package com.burakovv.algorithms;

import com.burakovv.data.ComparableData;

import java.util.Random;

public class QuickSort {
    private static final Random random = new Random(31415926);

    public static void sort(ComparableData data) {
        sort(data, data.getOffset(), data.getOffset() + data.getSize() - 1);
    }

    private static void sort(ComparableData data, int left, int right) {
        if (left >= right) {
            return;
        }
        int k = randomPartition(data, left, right);
        sort(data, left, k - 1);
        sort(data, k + 1, right);
    }

    private static int randomPartition(ComparableData data, int left, int right) {
        int k = left + random.nextInt(right - left);
        data.swap(k, right);
        return partition(data, left, right);
    }

    private static int partition(ComparableData data, int left, int right) {
        int i = left;
        for (int j = left; j <= right; j++) {
            if (data.compare(j, right) <= 0) {
                data.swap(i, j);
                i++;
            }
        }
        return i - 1;
    }

}

package com.burakovv.algorithms;

import com.burakovv.data.ComparableData;

public class OrderStatistic {

    /**
     * This method can modify input data.
     * @return the index of element that shall be on {@code k} position after sorting the data
     * (but without actually sorting the data)
     */
    public static int select(ComparableData data, int k) {
        return select(data.getOffset() + k, data, data.getOffset(), data.getOffset() + data.getSize() - 1);
    }

    private static int select(int k, ComparableData data, int left, int right) {
        if (left == right) {
            return k;
        }
        int p = QuickSort.randomPartition(data, left, right);
        if (p == k) {
            return p;
        } else if (p < k) {
            return select(k, data, p + 1, right);
        } else {
            return select(k, data, left, p - 1);
        }
    }

}
